package io.github.dingfeiyang.logic.mapper;

import io.github.dingfeiyang.PbUtil;
import io.github.dingfeiyang.model.ExternalAuthModel;
import io.github.dingfeiyang.model.RecordModel;
import io.github.dingfeiyang.persistence.dbx.expression.Expression;
import io.github.dingfeiyang.persistence.mapper.AbstractMapper;
import io.github.dingfeiyang.util.PbConstants;

import java.util.List;
import java.util.Map;

public class ExternalAuthMapper extends AbstractMapper<ExternalAuthModel> {
    @Override
    public String getTableName() {
        return PbConstants.TableName.EXTERNAL_AUTHS;
    }


    public List<ExternalAuthModel> findAllExternalAuthsByRecord(RecordModel record) {
        return PbUtil.queryList(modelQuery().andWhere(Expression.newHashExpr(Map.of("collectionId", record.getCollection().getId(), "recordId", record.getId()))), ExternalAuthModel.class);
    }

}
