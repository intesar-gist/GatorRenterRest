package com.gsd.gatorrenter.utils.constant;

/**
 * Created by VD-Intesar on 5/9/2016.
 */
public enum ResponseStatusCode implements StatusCodeEnum<ResponseStatusCode> {

    SOMETHING_UNEXPECTED_HAPPENED (-1, "Something unexpected happened. "),
    SUCCESS (0, "OK"),
    INVALID_OBJ_PASSED (1, "Cannot cast object. "),
    EMAIL_MISSING (2, "Email is missing. "),
    PWD_MISSING (3, "Password is missing. "),
    USER_ALREADY_EXISTS (4, "User already exists with similar email. "),
    NO_ROLE_DEFINED (5, "User role is not defined. "),
    UNAUTHENTICATED_CLIENT (6, "Invalid or no credentials (userId, password or Token) passed in request headers, cannot authenticate user. "),
    USER_NOT_FOUND (7, "User details not found in database"),
    MISSING_USER_ID (8, "User ID is missing in the request body. ");

    private Integer code;
    private String statusMessage;

    private ResponseStatusCode(int code, String statusMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getStatusCodeStr() {
//        return StatusCodePrefix.ITINERARIES_PROCESS_ERROR_TYPE.getStatusCodePrefix() + String.format("%04d", code); //not using prfix for now, will use in future
        return Integer.toString(code);
    }

    @Override
    public String getStatusMessage() {
        return statusMessage;
    }

    public static ResponseStatusCode getResponseStatus(Integer code) {
        for(ResponseStatusCode e : values()) {
            if(e.code == code) return e;
        }
        return null;
    }
}
