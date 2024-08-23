package io.github.dingfeiyang.persistence.mapper;

import io.github.dingfeiyang.persistence.dbx.Query;
import io.github.dingfeiyang.persistence.dbx.SelectQuery;
import io.github.dingfeiyang.persistence.dbx.expression.Expression;

/**
 * The parent class of the all mappers.
 **/

public interface PbMapper {

    /**
     * Get the name of table.
     *
     * @return The name of table.
     */
    String getTableName();

    /**
     * Get the datasource name.
     *
     * @return The name of datasource.
     */
    default String getDataSource() {
        return PbMapperManager.DEFAULT_DATA_SOURCE;
    }


    SelectQuery modelQuery();

    SelectQuery findBy(Expression expression);

    Query insertQuery(Object data, String... includeFields);

    Query insertQuery(Object data, MappingOptions options);

    Query delete(Expression where);

    Query updateQuery(Object data, Expression where, String... includeFields);

    Query updateQuery(Object data, Expression where, MappingOptions options);
}