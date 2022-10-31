package ru.open.restApplication.controller;

import org.springframework.http.MediaType;
import ru.open.restApplication.request.ChangeUserStatusRequest;
import ru.open.restApplication.response.ChangeUserStatusResponse;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.exeption.UserNotFoundException;
import ru.open.restApplication.repository.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)), HttpStatus.OK);
    }

    /**
     * TODO возвращать только id пользователя
     * TODO Как валидироват пейлод?
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws IOException {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    /**
     * TODO уникальный ID (timestamp) запроса.
     */
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ChangeUserStatusResponse> changeUserStatus(@PathVariable("id") Long id, @RequestBody() ChangeUserStatusRequest status) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<>(userService.changeStatus(user, status.getStatus()), HttpStatus.OK);
    }

    /**
     * Возвращать только URL картинки
     */
    @PostMapping("/{id}/avatar")
    public ResponseEntity<User> uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile avatar) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userService.uploadAvatar(user, avatar);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
