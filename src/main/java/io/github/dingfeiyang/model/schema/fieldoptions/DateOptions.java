package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.RuleFunc;
import io.github.dingfeiyang.util.PbConstants;
import io.github.dingfeiyang.validator.Validation;

import java.time.LocalDateTime;


public class DateOptions implements FieldOptions {
    private LocalDateTime min;
    private LocalDateTime max;


    @Override
    public Errors validate() {
        return Validation.validateObject(this, Validation.field(DateOptions::getMax, Validation.by(checkRange(this.min, this.max))));
    }

    private RuleFunc checkRange(LocalDateTime min, LocalDateTime max) {
        return (Object value) -> {
            LocalDateTime v = (LocalDateTime) value;

            if (null == v || null == min || null == max) {
                return null; // nothing to check
            }


            return Validation.date(PbConstants.DefaultDateLayout) // TODO 需要验证
                    .min(min.toLocalTime())
                    .max(max.toLocalTime())
                    .validate(v.toString());
        };
    }

    public LocalDateTime getMin() {
        return min;
    }

    public DateOptions setMin(LocalDateTime min) {
        this.min = min;
        return this;
    }

    public LocalDateTime getMax() {
        return max;
    }

    public DateOptions setMax(LocalDateTime max) {
        this.max = max;
        return this;
    }
}
