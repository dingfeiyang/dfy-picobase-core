package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;

public class CollectionCreateEvent implements PbEvent {

    public CollectionModel collection;
    public TimePosition timePosition;

    public CollectionCreateEvent(CollectionModel collection, TimePosition timePosition) {
        this.collection = collection;
        this.timePosition = timePosition;
    }
}
