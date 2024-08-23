package io.github.dingfeiyang.logic.mapper;

import io.github.dingfeiyang.model.AdminModel;
import io.github.dingfeiyang.persistence.dbx.SelectQuery;
import io.github.dingfeiyang.persistence.dbx.expression.Expression;
import io.github.dingfeiyang.persistence.mapper.AbstractMapper;
import io.github.dingfeiyang.util.PbConstants;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminMapper extends AbstractMapper<AdminModel> {


    @Override
    public String getTableName() {
        return PbConstants.TableName.ADMIN;
    }


    public SelectQuery isAdminEmailUnique(String email, String... excludeIds) {
        // 过滤掉null值并转换为Set确保唯一性
        Set<String> uniqueIds = Arrays.stream(excludeIds)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        SelectQuery sq = modelQuery().select("count(*)").where(Expression.newExpr("email=:email",
                Map.of("email", email))).limit(1);

        if (uniqueIds.size() > 0) {
            sq.andWhere(Expression.notIn("id", uniqueIds));
        }

        return sq;
    }


}
