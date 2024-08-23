package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;

public class RecordViewEvent implements PbEvent {
    public CollectionModel collection;
    public RecordModel record;

    public RecordViewEvent(CollectionModel collection, RecordModel record) {
        this.collection = collection;
        this.record = record;
    }
}
