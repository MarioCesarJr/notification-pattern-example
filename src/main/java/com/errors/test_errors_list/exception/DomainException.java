package com.errors.test_errors_list.exception;

import com.errors.test_errors_list.service.notification.NotificationError;

public class DomainException extends RuntimeException {

    private final NotificationError notificationError;

    public DomainException(NotificationError notificationError) {
        this.notificationError = notificationError;
    }

    public DomainException(String message) {
        super(message);
        this.notificationError = new NotificationError();
    }

    public NotificationError getNotificationError() {
        return notificationError;
    }
}
