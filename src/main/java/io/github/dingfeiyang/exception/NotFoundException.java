package io.github.dingfeiyang.exception;

import static io.github.dingfeiyang.error.PbErrorCode.CODE_404;

public class NotFoundException extends PbException {


    @Override
    public int getCode() {
        if (super.getCode() == 0) {
            return CODE_404;
        }
        return super.getCode();
    }


    public NotFoundException() {
        super("NotFoundException");
    }

    public NotFoundException(String message) {
        super(message);
    }


}
