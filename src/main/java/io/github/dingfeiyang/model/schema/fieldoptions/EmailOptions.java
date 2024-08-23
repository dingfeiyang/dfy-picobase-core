package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Is;
import io.github.dingfeiyang.validator.Validation;

import java.util.List;


public class EmailOptions implements FieldOptions {
    private List<String> exceptDomains;
    private List<String> onlyDomains;


    @Override
    public Errors validate() {
        return Validation.validateObject(this,
                Validation.field(EmailOptions::getExceptDomains, Validation.when(null != onlyDomains && onlyDomains.size() > 0, Validation.Empty).otherwise(Validation.each(Is.Domain))),
                Validation.field(EmailOptions::getOnlyDomains, Validation.when(null != exceptDomains && exceptDomains.size() > 0, Validation.Empty).otherwise(Validation.each(Is.Domain))));
    }

    public List<String> getExceptDomains() {
        return exceptDomains;
    }

    public EmailOptions setExceptDomains(List<String> exceptDomains) {
        this.exceptDomains = exceptDomains;
        return this;
    }

    public List<String> getOnlyDomains() {
        return onlyDomains;
    }

    public EmailOptions setOnlyDomains(List<String> onlyDomains) {
        this.onlyDomains = onlyDomains;
        return this;
    }
}
