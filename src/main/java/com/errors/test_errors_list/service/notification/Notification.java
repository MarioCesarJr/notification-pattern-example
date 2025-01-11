package com.errors.test_errors_list.service.notification;

import java.util.List;

import com.errors.test_errors_list.dto.ErrorResponse;

public interface Notification {

    public void addError(String message);

    public Boolean hasErrors();

    public List<String> getErrors();

    public ErrorResponse getErrorResponse();
}
