package ru.open.restApplication.criteria;

import lombok.*;
import ru.open.restApplication.entity.User;

@Getter
@Setter
@RequiredArgsConstructor
public class UserSearchCriteria {
    private int page = 0;
    private int size = 25;
    private User.Status status;
    private Long timestamp;
}
