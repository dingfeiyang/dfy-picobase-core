package io.github.dingfeiyang.validator;

/**
 * Rule represents a validation rule.
 */
public interface Rule {
    Err validate(Object value);

}
