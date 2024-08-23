package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.AdminModel;

public class AdminUpdateEvent implements PbEvent {
    public AdminModel admin;
    public TimePosition timePosition;

    public AdminUpdateEvent(AdminModel admin, TimePosition timePosition) {
        this.admin = admin;
        this.timePosition = timePosition;
    }
}
