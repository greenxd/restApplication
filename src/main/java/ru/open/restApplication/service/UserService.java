package ru.open.restApplication.service;

import lombok.RequiredArgsConstructor;
import ru.open.restApplication.response.ChangeUserStatusResponse;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageService imageService;

    /**
     * TODO Добавить проверку на тип файла JPG
     */
    public User uploadAvatar(User user, MultipartFile avatar) throws IOException {
        String avatarPath = imageService.store(avatar, user.getUploadDir());
        user.setAvatar(avatarPath);

        return userRepository.save(user);
    }

    public ChangeUserStatusResponse changeStatus(User user, User.Status status) {
        ChangeUserStatusResponse statusResponse = new ChangeUserStatusResponse();
        statusResponse.setId(user.getId());
        statusResponse.setCurrentStatus(user.getIsOnline());
        statusResponse.setNewStatus(status);

        user.setIsOnline(status);
        userRepository.save(user);

        return statusResponse;
    }
}
