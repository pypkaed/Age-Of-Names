package ru.zarina.erudite.exceptions;

public class FileServiceExceptionHandler {
    public static FileServiceException handleHumanServiceException(HumanServiceException e) {
        return new FileServiceException("Error: " + e.getMessage() + " Skipping entry.");
    }
}
