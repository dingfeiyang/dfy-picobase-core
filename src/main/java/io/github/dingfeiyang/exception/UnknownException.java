package io.github.dingfeiyang.exception;

import io.github.dingfeiyang.util.StrFormatter;

public class UnknownException extends PbException {

    public UnknownException(Throwable cause) {
        super(cause);
    }

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String template, Object... args) {
        super(StrFormatter.format(template, args));
    }

    public UnknownException(Throwable cause, String message) {
        super(message, cause);
    }

    public UnknownException(Throwable cause, String template, Object... args) {
        super(StrFormatter.format(template, args), cause);
    }
}
