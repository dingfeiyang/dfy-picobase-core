package io.github.dingfeiyang.model.event;

import io.github.dingfeiyang.event.PbEvent;
import io.github.dingfeiyang.model.AdminModel;
import io.github.dingfeiyang.persistence.repository.Page;

public class AdminsListEvent implements PbEvent {
    public Page<AdminModel> result;

    public AdminsListEvent(Page<AdminModel> result) {
        this.result = result;
    }

}
