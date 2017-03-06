package com.gsd.gatorrenter.dto;

import java.io.Serializable;

/**
 * Created by Intesar on 3/5/2017.
 */
public class StatusDto implements Serializable {

    private Integer statusCode;
    private String  statusMessage;
    private Boolean success;

    public StatusDto() {
    }

    public StatusDto(Integer statusCode, String statusMessage, Boolean success) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
