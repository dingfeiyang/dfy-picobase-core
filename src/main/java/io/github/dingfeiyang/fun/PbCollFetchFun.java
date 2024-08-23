package io.github.dingfeiyang.fun;


import io.github.dingfeiyang.model.CollectionModel;

import java.util.Optional;

@FunctionalInterface
public interface PbCollFetchFun {
    Optional<CollectionModel> findByIdOrName(String idOrName);
}
