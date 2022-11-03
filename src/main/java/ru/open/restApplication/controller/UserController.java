package ru.open.restApplication.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import ru.open.restApplication.criteria.UserCriteria;
import ru.open.restApplication.request.ChangeUserStatusRequest;
import ru.open.restApplication.response.ChangeUserStatusResponse;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.exeption.UserNotFoundException;
import ru.open.restApplication.repository.UserRepository;
import ru.open.restApplication.response.UserAvatarResponse;
import ru.open.restApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.get(id).orElseThrow(() -> new UserNotFoundException(id)), HttpStatus.OK);
    }

    /**
     * TODO возвращать только id пользователя
     * TODO Как валидироват пейлод?
     */
    @Order
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws IOException {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    /**
     * TODO уникальный ID (timestamp) запроса.
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @GetMapping
    public ResponseEntity<List<User>> listUsers(@RequestParam(value = "status", required = false) User.Status status,
                                                @RequestParam(value = "id", required = false) Long id) {
        UserCriteria criteria = new UserCriteria();
        criteria.setStatus(status);
        criteria.setTimestamp(id);
        return new ResponseEntity<>(userService.list(new UserCriteria()), HttpStatus.OK);
    }

    @Order
    @PatchMapping("/{id}/status")
    public ResponseEntity<ChangeUserStatusResponse> changeUserStatus(@PathVariable("id") Long id, @RequestBody() ChangeUserStatusRequest status) throws UserNotFoundException {
        User user = userService.get(id).orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<>(userService.changeStatus(user, status.getStatus()), HttpStatus.OK);
    }

    @Order
    @PostMapping("/{id}/avatar")
    public ResponseEntity<UserAvatarResponse> uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile avatar) throws IOException {
        User user = userService.get(id).orElseThrow(() -> new UserNotFoundException(id));
        userService.uploadAvatar(user, avatar);
        UserAvatarResponse response = new UserAvatarResponse();
        response.setAvatar(user.getAvatar());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
