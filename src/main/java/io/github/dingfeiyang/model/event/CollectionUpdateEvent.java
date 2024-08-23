package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;

public class CollectionUpdateEvent implements PbEvent {

    public CollectionModel collection;
    public TimePosition timePosition;

    public CollectionUpdateEvent(CollectionModel collection, TimePosition timePosition) {
        this.collection = collection;
        this.timePosition = timePosition;
    }
}
