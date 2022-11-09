package ru.open.restApplication.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAvatarResponse {
    @NonNull
    public String avatar;
}
