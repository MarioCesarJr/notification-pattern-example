package com.errors.test_errors_list.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.errors.test_errors_list.dto.UserDto;
import com.errors.test_errors_list.exception.DomainException;
import com.errors.test_errors_list.model.User;
import com.errors.test_errors_list.service.UserService;
import com.errors.test_errors_list.service.notification.NotificationError;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {

        try {
            User user = userService.create(userDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DomainException e) {
            NotificationError notificationError = e.getNotificationError();
            return new ResponseEntity<>(notificationError.getErrorResponse(), HttpStatus.BAD_REQUEST); 
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
