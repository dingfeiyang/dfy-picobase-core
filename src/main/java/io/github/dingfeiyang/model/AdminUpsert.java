package io.github.dingfeiyang.model;

import cn.hutool.core.util.StrUtil;
import io.github.dingfeiyang.PbUtil;
import io.github.dingfeiyang.logic.mapper.AdminMapper;
import io.github.dingfeiyang.persistence.dbx.SelectQuery;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Is;
import io.github.dingfeiyang.validator.RuleFunc;

import static io.github.dingfeiyang.model.validators.Validators.uniqueId;
import static io.github.dingfeiyang.util.PbConstants.*;
import static io.github.dingfeiyang.validator.Err.newError;
import static io.github.dingfeiyang.validator.Validation.*;

public class AdminUpsert {

    private String id;
    private String email;
    private String password;
    private String passwordConfirm;
    private int avatar;

    public String getId() {
        return id;
    }

    public AdminUpsert setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminUpsert setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminUpsert setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public AdminUpsert setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
        return this;
    }

    public int getAvatar() {
        return avatar;
    }

    public AdminUpsert setAvatar(int avatar) {
        this.avatar = avatar;
        return this;
    }

    /**
     * 对dto进行校验
     *
     * @param originalAdmin 数据库中的原始对象，用于校验
     * @return 校验结果
     */
    public Errors validate(AdminModel originalAdmin) {
        if (originalAdmin == null) {
            originalAdmin = new AdminModel();
        }
        boolean isCreate = StrUtil.isEmpty(originalAdmin.getId());
        return PbUtil.validate(this,
                field("id", this.id,
                        when(isCreate,
                                length(DEFAULT_ID_LENGTH, DEFAULT_ID_LENGTH),
                                match(ID_REGEX_P),
                                by(uniqueId(TableName.ADMIN))
                        )
                ),
                field("avatar", this.avatar, min(0), max(9)),
                field("email", this.email, required, length(1, 255), Is.EmailFormat, by(isAdminEmailUnique(originalAdmin.getId()))),
                field("password", this.password,
                        when(isCreate, required, length(10, 72))),
                field("passwordConfirm", this.passwordConfirm,
                        when(StrUtil.isNotEmpty(this.password), required),
                        by(passwordMatch()))

        );
    }

    /**
     * 密码校验
     */
    private RuleFunc passwordMatch() {
        return passwordConfirm -> {
            if (StrUtil.isEmpty(this.password)) {
                return null;
            }
            return this.password.equals(passwordConfirm) ? null : newError("validation_values_mismatch", "Values don't match.");
        };
    }

    /**
     * 邮箱唯一性校验
     *
     * @return 校验函数
     */
    private RuleFunc isAdminEmailUnique(String originalId) {
        AdminMapper mapper = PbUtil.findMapper(AdminModel.class);
        return value -> {
            SelectQuery sq = mapper.isAdminEmailUnique((String) value, originalId);
            Integer i = sq.build().one(Integer.class);
            return i != null && i > 0 ? newError("validation_invalid_email", "The email is invalid or already exists.") : null;
        };
    }
}