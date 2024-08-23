package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;

public class RecordAuthWithPasswordEvent implements PbEvent {

    public CollectionModel collection;
    public String identity;

    public String password;

    public RecordModel record;

    public TimePosition timePosition;

    public RecordAuthWithPasswordEvent(CollectionModel collection, String identity, String password, RecordModel record, TimePosition timePosition) {
        this.collection = collection;
        this.identity = identity;
        this.password = password;
        this.record = record;
        this.timePosition = timePosition;
    }
}
