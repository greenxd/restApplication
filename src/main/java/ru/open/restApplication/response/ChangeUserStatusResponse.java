package ru.open.restApplication.response;

import lombok.Setter;
import ru.open.restApplication.entity.User;

@Setter
public class ChangeUserStatusResponse {
    private Long id;
    private User.Status currentStatus;
    private User.Status newStatus;

    public User.Status getCurrentStatus() {
        return currentStatus;
    }

    public User.Status getNewStatus() {
        return newStatus;
    }
}
