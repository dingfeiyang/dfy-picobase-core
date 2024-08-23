package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;
import io.github.dingfeiyang.persistence.repository.Page;

public class RecordsListEvent implements PbEvent {
    public CollectionModel collection;
    public Page<RecordModel> result;


    public RecordsListEvent(CollectionModel collection, Page<RecordModel> result) {
        this.collection = collection;
        this.result = result;
    }

}
