package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.persistence.repository.Page;

import java.util.List;

public class CollectionsListEvent implements PbEvent {

    public List<CollectionModel> collections;
    public Page<CollectionModel> result;

    public CollectionsListEvent(List<CollectionModel> items, Page<CollectionModel> result) {
        this.collections = items;
        this.result = result;
    }
}
