package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.CollectionModel;

public class CollectionViewEvent implements PbEvent {
    public CollectionModel collection;

    public CollectionViewEvent(CollectionModel collection) {
        this.collection = collection;
    }
}
