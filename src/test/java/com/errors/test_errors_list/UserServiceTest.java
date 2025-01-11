package com.errors.test_errors_list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.errors.test_errors_list.dto.UserDto;
import com.errors.test_errors_list.exception.DomainException;
import com.errors.test_errors_list.model.User;
import com.errors.test_errors_list.service.UserService;

@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void shouldCreateUserWhenValidData() {
        
        UserDto userDto = new UserDto("Mazaropi da Silva", "mazaropi.silva@example.com");

        User user = userService.create(userDto);

        assertNotNull(user);
        assertEquals("Mazaropi da Silva", user.getName());
        assertEquals("mazaropi.silva@example.com", user.getEmail());
    }

    @Test
    void shouldThrowBusinessExceptionWhenNameIsMissing() {
       
        UserDto userDto = new UserDto(null, "mazaropi.silva@example.com");

        DomainException thrown = assertThrows(DomainException.class, () -> {
            userService.create(userDto);
        });

        assertTrue(thrown.getNotificationError().hasErrors());
        assertTrue(thrown.getNotificationError().getErrors().contains("Name is required"));
    }

    @Test
    void shouldThrowBusinessExceptionWhenEmailIsMissing() {
       
        UserDto userDto = new UserDto("Mazaropi da Silva", null);

        DomainException thrown = assertThrows(DomainException.class, () -> {
            userService.create(userDto);
        });

        assertTrue(thrown.getNotificationError().hasErrors());
        assertTrue(thrown.getNotificationError().getErrors().contains("Email is required"));
    }

    @Test
    void shouldThrowBusinessExceptionWhenBothNameAndEmailAreMissing() {
        
        UserDto userDto = new UserDto(null, null);

        DomainException thrown = assertThrows(DomainException.class, () -> {
            userService.create(userDto);
        });

        assertTrue(thrown.getNotificationError().hasErrors());
        assertTrue(thrown.getNotificationError().getErrors().contains("Name is required"));
        assertTrue(thrown.getNotificationError().getErrors().contains("Email is required"));
    }
}
