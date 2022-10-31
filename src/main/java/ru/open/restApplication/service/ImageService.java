package ru.open.restApplication.service;

import ru.open.restApplication.exeption.EmptyFileException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    public String store(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.isEmpty()) {
            throw new EmptyFileException();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadDir = Paths.get(path);
        Path filePath = uploadDir.resolve(fileName);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioException) {
            throw new IOException("Could not save file: " + fileName, ioException);
        }

        return filePath.toString();
    }
}
