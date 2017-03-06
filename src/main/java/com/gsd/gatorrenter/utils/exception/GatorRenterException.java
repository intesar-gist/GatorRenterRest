package com.gsd.gatorrenter.utils.exception;

import com.gsd.gatorrenter.utils.constant.ResponseStatusCode;
import com.gsd.gatorrenter.utils.constant.StatusCodeEnum;

/**
 * Created by Intesar on 3/5/2017.
 */
public class GatorRenterException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    public GatorRenterException(String ms) {
        this(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED, ms);
    }

    protected GatorRenterException(String ms, String code) {
        super(ms);
        this.code = code;
    }

    protected GatorRenterException(String ms, String code, Throwable throwable) {
        super(ms, throwable);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public GatorRenterException(StatusCodeEnum<? extends Enum<?>> statusCodeEnum) {
        this(statusCodeEnum.getStatusMessage(), statusCodeEnum.getStatusCodeStr());
    }

    public GatorRenterException(StatusCodeEnum<? extends Enum<?>> statusCodeEnum, Boolean useParameters, String... parameters) {
        this(String.format(statusCodeEnum.getStatusMessage(), parameters), statusCodeEnum.getStatusCodeStr());
    }

    public GatorRenterException(StatusCodeEnum<? extends Enum<?>> statusCodeEnum, Object object) {
        this(statusCodeEnum.getStatusMessage() + "[" + object + "]", statusCodeEnum.getStatusCodeStr());
    }

    public GatorRenterException(StatusCodeEnum<? extends Enum<?>> statusCodeEnum, Object object, Throwable ex) {
        this(statusCodeEnum.getStatusMessage() + "[" + object + "]", statusCodeEnum.getStatusCodeStr(), ex);
    }

    public GatorRenterException(Exception ex) {
        this(ResponseStatusCode.SOMETHING_UNEXPECTED_HAPPENED, ex.getMessage(), ex);
    }
}
