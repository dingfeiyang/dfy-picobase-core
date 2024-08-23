package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Validation;

public class JsonOptions implements FieldOptions {

    private int maxSize;

    public JsonOptions(int maxSize) {
        this.maxSize = maxSize;
    }

    public JsonOptions() {
    }

    @Override
    public Errors validate() {
        return Validation.validateObject(this,
                Validation.field(JsonOptions::getMaxSize, Validation.required, Validation.min(1)));
    }

    public int getMaxSize() {
        return maxSize;
    }
}
