package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.file.PbFile;
import io.github.dingfeiyang.model.CollectionModel;
import io.github.dingfeiyang.model.RecordModel;

import java.util.List;
import java.util.Map;

public class RecordUpdateEvent implements PbEvent {
    public CollectionModel collection;
    public RecordModel record;
    public Map<String, List<PbFile>> uploadedFiles;
    public TimePosition timePosition;

    public RecordUpdateEvent(CollectionModel collection, RecordModel record, Map<String, List<PbFile>> uploadedFiles, TimePosition timePosition) {
        this.collection = collection;
        this.record = record;
        this.uploadedFiles = uploadedFiles;
        this.timePosition = timePosition;
    }
}
