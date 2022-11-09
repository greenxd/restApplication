package ru.open.restApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.open.restApplication.entity.User;
import ru.open.restApplication.repository.UserRepository;
import ru.open.restApplication.service.ImageService;
import ru.open.restApplication.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserRepository.class, ImageService.class})
public class UserServiceTests {
    @MockBean
    private UserService userService;
    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1L)
                .email("test@gmail.com")
                .name("ivan")
                .avatar(null)
                .isOnline(User.Status.OFFLINE)
                .build();
    }

    @Test
    public void testCreateUserSuccess() {
        given(userService.create(user)).willReturn(user);
    }

    @Test
    public void testUserPaginatedListSuccess() {
        PageRequest peagable = PageRequest.of(1, 10);
        List<User> users = Arrays.asList(new User(), new User());
        Page<User> usersPage = new PageImpl<>(users, peagable, users.size());

        given(userService.list(User.Status.OFFLINE, 1L, peagable)).willReturn(usersPage);
    }

    @Test
    public void tesUserGetSuccess() {
        given(userService.get(1L)).willReturn(Optional.of(user));
    }
}
