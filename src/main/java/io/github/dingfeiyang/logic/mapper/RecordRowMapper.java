package io.github.dingfeiyang.logic.mapper;

import io.github.dingfeiyang.exception.PbException;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;
import io.github.dingfeiyang.model.schema.SchemaField;
import io.github.dingfeiyang.persistence.repository.PbRowMapper;
import io.github.dingfeiyang.util.PbConstants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RecordRowMapper implements PbRowMapper<RecordModel> {
    private final CollectionModel collection;


    public RecordRowMapper(CollectionModel collection) {
        this.collection = collection;
    }

    @Override
    public RecordModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return newRecordFromResultSet(collection, rs);
    }


    /**
     * 从 ResultSet 初始化 RecordModel
     */
    private RecordModel newRecordFromResultSet(CollectionModel collection, ResultSet rs) {
        Map<String, Object> resultMap;
        try {
            resultMap = new HashMap<>(rs.getMetaData().getColumnCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // load schema fields
        for (SchemaField field : collection.getSchema().getFields()) {
            try {
                resultMap.put(field.getName(), rs.getObject(field.getName()));
            } catch (SQLException e) {
                //ignore exception
                //resultMap.put(field.getName(), null);
                throw new PbException(e);
            }
        }
        // load base model fields
        for (String name : PbConstants.baseModelFieldNames) {
            try {
                resultMap.put(name, rs.getObject(name));
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                resultMap.put(name, null);
            }
        }

        // load auth fields
        if (collection.isAuth()) {
            for (String name : PbConstants.authFieldNames) {
                try {
                    resultMap.put(name, rs.getObject(name));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        var record = new RecordModel(collection);
        record.load(resultMap);
        // 数据库中加载的record new 属性均为 false；
        record.setNew(false);
        //record.publicExport(); //公共字段和结果导出 ,最终还会被Controller后置AOP拦截器修改 根据 request 中 fields param 修改publicData
        return record;
    }


}
