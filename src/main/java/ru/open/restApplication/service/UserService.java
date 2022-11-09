package ru.open.restApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.open.restApplication.config.RestApplicationParameters;
import ru.open.restApplication.exeption.EmptyFileException;
import ru.open.restApplication.exeption.UserNotFoundException;
import ru.open.restApplication.response.ChangeUserStatusResponse;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageService imageService;

    @Autowired
    RestApplicationParameters parameters;

    public User uploadAvatar(Long userId, MultipartFile avatar) throws IOException, EmptyFileException, UserNotFoundException {
        User user = this.get(userId).orElseThrow(() -> new UserNotFoundException(userId));
        String avatarPath = imageService.store(avatar, parameters.getUploadPath());
        user.setAvatar(avatarPath);

        return userRepository.save(user);
    }

    public ChangeUserStatusResponse changeStatus(Long id, User.Status status) throws UserNotFoundException {
        User user = this.get(id).orElseThrow(() -> new UserNotFoundException(id));

        ChangeUserStatusResponse statusResponse = new ChangeUserStatusResponse(user.getId(), user.getIsOnline(), status);

        user.setIsOnline(status);
        userRepository.save(user);

        return statusResponse;
    }

    public Page<User> list(User.Status status, Long id, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
