package ru.open.restApplication.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import ru.open.restApplication.criteria.UserSearchCriteria;
import ru.open.restApplication.exeption.EmptyFileException;
import ru.open.restApplication.request.ChangeUserStatusRequest;
import ru.open.restApplication.response.ChangeUserStatusResponse;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.exeption.UserNotFoundException;
import ru.open.restApplication.response.UserAvatarResponse;
import ru.open.restApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserService userService;

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.get(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Order
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    /**
     * TODO уникальный ID (timestamp) запроса.
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @GetMapping
    public Page<User> listUsers(@RequestParam(value = "status", required = false) User.Status status,
                                @RequestParam(value = "id", required = false) Long id,
                                Pageable pageable
    ) {
        return userService.list(status, id, pageable);
    }

    @Order
    @PatchMapping("/{id}/status")
    public ChangeUserStatusResponse changeUserStatus(@PathVariable("id") Long id, @RequestBody() ChangeUserStatusRequest status) throws UserNotFoundException {
        return userService.changeStatus(id, status.getStatus());
    }

    @Order
    @PostMapping("/{id}/avatar")
    public UserAvatarResponse uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile avatar) throws UserNotFoundException, EmptyFileException, IOException {
        User user = userService.uploadAvatar(id, avatar);

        return new UserAvatarResponse(user.getAvatar());
    }
}
