package ru.open.restApplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import ru.open.restApplication.exeption.EmptyFileException;
import ru.open.restApplication.service.ImageService;

import java.io.IOException;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ImageServiceTest {

    @MockBean
    private ImageService imageService;
    @Configuration
    public static class RestApplicationParameters {};

    @Test
    public void StoreImageSuccessful() throws EmptyFileException, IOException {
        final String path = "test";
        MockMultipartFile file = new MockMultipartFile(
                "avatar",
                "hello.jpeg",
                MediaType.IMAGE_JPEG_VALUE,
                new byte[] { 1 }
        );

        given(imageService.store(file, path)).willReturn(path);
    }
}
