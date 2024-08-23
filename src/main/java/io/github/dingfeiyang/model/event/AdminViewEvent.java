package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.AdminModel;

public class AdminViewEvent implements PbEvent {

    public AdminModel admin;

    public AdminViewEvent(AdminModel admin) {
        this.admin = admin;
    }
}
