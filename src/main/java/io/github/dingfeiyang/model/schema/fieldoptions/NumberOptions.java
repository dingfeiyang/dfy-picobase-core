package io.github.dingfeiyang.model.schema.fieldoptions;


import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.Rule;
import io.github.dingfeiyang.validator.RuleFunc;
import io.github.dingfeiyang.validator.*;

public class NumberOptions implements FieldOptions {
    private Integer min;
    private Integer max;
    private boolean noDecimal;


    @Override
    public Errors validate() {
        Rule[] maxRules = new Rule[2];
        if (null != min && null != max) {
            maxRules[0] = Validation.min(this.max);
            maxRules[1] = Validation.by(this.checkNoDecimal());
        }

        return Validation.validateObject(this,
                Validation.field(NumberOptions::getMin, Validation.by(this.checkNoDecimal())),
                Validation.field(NumberOptions::getMax, maxRules)
        );
    }

    private RuleFunc checkNoDecimal() {
        return (Object value) -> {
            Double v = (Double) value;
            if (v == null || !noDecimal) {
                return null; // nothing to check
            }

            if (!v.equals(Math.floor(v))) {
                return Err.newError("validation_no_decimal_constraint", "Decimal numbers are not allowed.");
            }
            return null;
        };
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public boolean isNoDecimal() {
        return noDecimal;
    }
}
