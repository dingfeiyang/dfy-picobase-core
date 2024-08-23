package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;

public class RecordDeleteEvent implements PbEvent {
    public CollectionModel collection;
    public RecordModel record;

    public TimePosition timePosition;

    public RecordDeleteEvent(CollectionModel collection, RecordModel record, TimePosition timePosition) {
        this.collection = collection;
        this.record = record;
        this.timePosition = timePosition;
    }
}
