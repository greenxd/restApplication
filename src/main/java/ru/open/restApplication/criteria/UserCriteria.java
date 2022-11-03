package ru.open.restApplication.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.open.restApplication.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserCriteria {
    private int page = 0;
    private int size = 25;
    private User.Status status;
    private Long timestamp;
}
