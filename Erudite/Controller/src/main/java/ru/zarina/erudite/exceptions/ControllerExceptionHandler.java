package ru.zarina.erudite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.zarina.erudite.exceptions.FileServiceException;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseBody
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = { IOException.class })
    @ResponseBody
    public ResponseEntity<String> handleIOException(IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error has occurred: " + e.getMessage());
    }

    @ExceptionHandler(value = { FileServiceException.class })
    @ResponseBody
    public ResponseEntity<String> handleFileServiceException(FileServiceException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("A parsing error has occurred: " + e.getMessage());
    }

    @ExceptionHandler(value = { HumanServiceException.class })
    @ResponseBody
    public ResponseEntity<String> handleHumanServiceException(HumanServiceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("An error has occurred: " + e.getMessage());
    }

    @ExceptionHandler(value = { RuntimeException.class })
    @ResponseBody
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("A runtime error has occurred: " + e.getMessage());
    }
}
