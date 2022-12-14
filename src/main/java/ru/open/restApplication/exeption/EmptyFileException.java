package ru.open.restApplication.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmptyFileException extends Exception {

    public EmptyFileException() {
        super("Empty file");
    }
}
