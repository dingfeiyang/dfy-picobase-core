package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.model.schema.MultiValuer;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Validation;

import java.util.List;

public class SelectOptions implements FieldOptions, MultiValuer {
    private int maxSelect;
    private List<String> values;
    

    //@JsonIgnore
    @Override
    public boolean isMultiple() {
        return this.maxSelect > 1;
    }


    @Override
    public Errors validate() {
        int max = values.size();
        if (max == 0) {
            max = 1;
        }

        return Validation.validateObject(this,
                Validation.field(SelectOptions::getValues, Validation.required),
                Validation.field(SelectOptions::getMaxSelect, Validation.min(1), Validation.max(max))
        );
    }

    public int getMaxSelect() {
        return maxSelect;
    }

    public SelectOptions setMaxSelect(int maxSelect) {
        this.maxSelect = maxSelect;
        return this;
    }

    public List<String> getValues() {
        return values;
    }

    public SelectOptions setValues(List<String> values) {
        this.values = values;
        return this;
    }
}
