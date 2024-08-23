package io.github.dingfeiyang.model.schema.fieldoptions;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Is;
import io.github.dingfeiyang.validator.Rule;
import io.github.dingfeiyang.validator.Validatable;
import io.github.dingfeiyang.validator.*;

import java.util.List;


/**
 * CollectionAuthOptions defines the "auth" Collection.Options fields
 *
 * @author : clown
 * @date : 2024-03-05 13:26
 **/
public class CollectionAuthOptions implements Validatable {
    private String manageRule;
    private boolean allowOAuth2Auth;
    private boolean allowUsernameAuth;
    private boolean allowEmailAuth;
    private boolean requireEmail;
    private List<String> exceptEmailDomains;
    private boolean onlyVerified;
    private List<String> onlyEmailDomains;
    private int minPasswordLength;


    @Override
    public Errors validate() {
        Rule nullOrNotEmpty = value -> {
            if (value == null || !StrUtil.isEmptyIfStr(value)) {
                return null;
            }
            return Err.newError("validation_nil_or_not_empty_required", "cannot be blank");
        };
        return Validation.validateObject(this,
                Validation.field(CollectionAuthOptions::getManageRule, nullOrNotEmpty),
                Validation.field(CollectionAuthOptions::getExceptEmailDomains, Validation.when(CollUtil.isEmpty(onlyEmailDomains), Validation.Empty).otherwise(Validation.each(Is.Domain))),
                Validation.field(CollectionAuthOptions::getOnlyEmailDomains, Validation.when(CollUtil.isEmpty(exceptEmailDomains), Validation.Empty).otherwise(Validation.each(Is.Domain))),
                Validation.field(CollectionAuthOptions::getMinPasswordLength, Validation.when(this.allowUsernameAuth || this.allowEmailAuth, Validation.required, Validation.min(5), Validation.max(72))));
    }

    public String getManageRule() {
        return manageRule;
    }

    public CollectionAuthOptions setManageRule(String manageRule) {
        this.manageRule = manageRule;
        return this;
    }

    public boolean isAllowOAuth2Auth() {
        return allowOAuth2Auth;
    }

    public CollectionAuthOptions setAllowOAuth2Auth(boolean allowOAuth2Auth) {
        this.allowOAuth2Auth = allowOAuth2Auth;
        return this;
    }

    public boolean isAllowUsernameAuth() {
        return allowUsernameAuth;
    }

    public CollectionAuthOptions setAllowUsernameAuth(boolean allowUsernameAuth) {
        this.allowUsernameAuth = allowUsernameAuth;
        return this;
    }

    public boolean isAllowEmailAuth() {
        return allowEmailAuth;
    }

    public CollectionAuthOptions setAllowEmailAuth(boolean allowEmailAuth) {
        this.allowEmailAuth = allowEmailAuth;
        return this;
    }

    public boolean isRequireEmail() {
        return requireEmail;
    }

    public CollectionAuthOptions setRequireEmail(boolean requireEmail) {
        this.requireEmail = requireEmail;
        return this;
    }

    public List<String> getExceptEmailDomains() {
        return exceptEmailDomains;
    }

    public CollectionAuthOptions setExceptEmailDomains(List<String> exceptEmailDomains) {
        this.exceptEmailDomains = exceptEmailDomains;
        return this;
    }

    public boolean isOnlyVerified() {
        return onlyVerified;
    }

    public CollectionAuthOptions setOnlyVerified(boolean onlyVerified) {
        this.onlyVerified = onlyVerified;
        return this;
    }

    public List<String> getOnlyEmailDomains() {
        return onlyEmailDomains;
    }

    public CollectionAuthOptions setOnlyEmailDomains(List<String> onlyEmailDomains) {
        this.onlyEmailDomains = onlyEmailDomains;
        return this;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public CollectionAuthOptions setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
        return this;
    }
}
