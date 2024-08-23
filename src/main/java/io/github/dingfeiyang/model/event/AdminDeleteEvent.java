package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.AdminModel;

public class AdminDeleteEvent implements PbEvent {

    public AdminModel admin;
    public TimePosition timePosition;

    public AdminDeleteEvent(AdminModel admin, TimePosition timePosition) {
        this.admin = admin;
        this.timePosition = timePosition;

    }
}
