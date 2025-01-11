package com.errors.test_errors_list.service.notification;

import java.util.ArrayList;
import java.util.List;

import com.errors.test_errors_list.dto.ErrorResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationError implements Notification {

    private int status;
    private List<String> errors = new ArrayList<>();

    @Override
    public void addError(String message) {
        this.errors.add(message);
    }

    @Override
    public Boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse response = new ErrorResponse(this.status, this.errors);
        return response;
    }

}
