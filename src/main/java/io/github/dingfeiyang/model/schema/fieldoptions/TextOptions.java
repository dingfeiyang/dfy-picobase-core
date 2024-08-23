package io.github.dingfeiyang.model.schema.fieldoptions;


import cn.hutool.core.util.StrUtil;
import io.github.dingfeiyang.validator.Err;
import io.github.dingfeiyang.validator.Errors;
import io.github.dingfeiyang.validator.RuleFunc;
import io.github.dingfeiyang.validator.Validation;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TextOptions implements FieldOptions {
    private Integer min;
    private Integer max;
    private String pattern;


    @Override
    public Errors validate() {
        int minVal = 0;
        if (min != null) {
            minVal = min;
        }

        return Validation.validateObject(this,
                Validation.field(TextOptions::getMin, Validation.min(0)),
                Validation.field(TextOptions::getMax, Validation.min(minVal)),
                Validation.field(TextOptions::getPattern, Validation.by(checkRegex()))
        );
    }

    private RuleFunc checkRegex() {
        return (Object value) -> {
            String v = (String) value;
            if (StrUtil.isBlank(v)) {
                return null; // nothing to check
            }

            try {
                Pattern.compile(v);
            } catch (PatternSyntaxException e) {
                return Err.newError("validation_invalid_regex", e.getMessage());
            }
            return null;
        };
    }

    public Integer getMin() {
        return min;
    }

    public TextOptions setMin(Integer min) {
        this.min = min;
        return this;
    }

    public Integer getMax() {
        return max;
    }

    public TextOptions setMax(Integer max) {
        this.max = max;
        return this;
    }

    public String getPattern() {
        return pattern;
    }

    public TextOptions setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }
}

