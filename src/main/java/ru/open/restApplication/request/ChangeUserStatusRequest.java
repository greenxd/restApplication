package ru.open.restApplication.request;

import lombok.Getter;
import lombok.Setter;
import ru.open.restApplication.entity.User;

@Setter
@Getter
public class ChangeUserStatusRequest {

    private User.Status status;
}
