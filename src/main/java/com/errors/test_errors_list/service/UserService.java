package com.errors.test_errors_list.service;

import org.springframework.stereotype.Service;

import com.errors.test_errors_list.dto.UserDto;
import com.errors.test_errors_list.exception.DomainException;
import com.errors.test_errors_list.model.User;
import com.errors.test_errors_list.service.notification.NotificationError;

@Service
public class UserService {

    private NotificationError notificationError;

    public User create(UserDto userDto) {
        notificationError = new NotificationError();

        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            this.notificationError.addError("Name is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            this.notificationError.addError("Email is required");
        }

        if (this.notificationError.hasErrors()) {
            this.notificationError.setStatus(400);
            throw new DomainException(this.notificationError);
        }

        User user = new User(userDto.getName(), userDto.getEmail());

        return user; 
    }
}
