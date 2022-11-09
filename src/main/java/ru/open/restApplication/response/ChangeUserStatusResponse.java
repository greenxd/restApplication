package ru.open.restApplication.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.open.restApplication.entity.User;

@AllArgsConstructor
@Getter
public class ChangeUserStatusResponse {
    private Long id;
    private User.Status currentStatus;
    private User.Status newStatus;
}
