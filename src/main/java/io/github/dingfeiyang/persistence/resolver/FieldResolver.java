package io.github.dingfeiyang.persistence.resolver;


import io.github.dingfeiyang.persistence.dbx.SelectQuery;

import java.util.List;

public interface FieldResolver {

    default void updateQuery(SelectQuery query) {

    }

    ResolverResult resolve(String field);


    static FieldResolver newSimpleFieldResolver(String... field) {
        return new SimpleFieldResolver(List.of(field));
    }
}
