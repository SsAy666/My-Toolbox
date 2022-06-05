package com.lzs.common;

import org.springframework.util.StringUtils;

public class ServiceException extends RuntimeException {
    private Integer status = ErrorStatus.NETWORK_IS_BUSY;
    private String message;
    private static final String ERROR_MESSAGE = "network is busy, please try again later";

    public ServiceException(String message) {
        super(!StringUtils.hasLength(message) ? ERROR_MESSAGE : message);
        this.message = !StringUtils.hasLength(message) ? ERROR_MESSAGE : message;
    }

    public ServiceException(Integer status, String message) {
        super(!StringUtils.hasLength(message) ? ERROR_MESSAGE : message);
        this.message = !StringUtils.hasLength(message) ? ERROR_MESSAGE : message;
        if (status != null) this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
