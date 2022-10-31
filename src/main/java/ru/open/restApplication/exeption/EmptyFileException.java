package ru.open.restApplication.exeption;

public class EmptyFileException extends RuntimeException {

    public EmptyFileException() {
        super("Empty file");
    }
}
