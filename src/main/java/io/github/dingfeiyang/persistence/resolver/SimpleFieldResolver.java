package io.github.dingfeiyang.persistence.resolver;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import io.github.dingfeiyang.persistence.dbx.DbxUtil;

import java.util.List;


public record SimpleFieldResolver(List<String> allowedFields) implements FieldResolver {

    @Override
    public ResolverResult resolve(String field) {
        if (!ListUtil.existInListWithRegex(field, allowedFields)) {
            throw new RuntimeException("Failed to resolve field " + field);
        }


        // var parts = field.split("."); 注意split的方法入参是正则，执行后 parts 为empty.
        List<String> parts = StrUtil.split(field, '.', -1);
        if (parts.size() == 1) {
            return ResolverResult.builder().identifier("`" + DbxUtil.columnify(parts.get(0)) + "`").build();
        }


        // treat as json path
        StringBuilder jsonPath = new StringBuilder("$");

        // 使用流 API
        parts.stream().skip(1).forEach(part -> {
            if (NumberUtil.isNumber(part)) {
                jsonPath.append("[").append(DbxUtil.columnify(part)).append("]");
            } else {
                jsonPath.append(".").append(part);
            }
        });

        return ResolverResult.builder().noCoalesce(true).identifier(
                String.format(
                        "JSON_EXTRACT(`%s`, '%s')", //TODO 修改为反引号
                        DbxUtil.columnify(parts.get(0)),
                        jsonPath)
        ).build();
    }

}
