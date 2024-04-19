package com.example.url.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundShortedUrlException.class)
    public ResponseEntity<String> handleNotFoundShortenUrlException(
            NotFoundShortedUrlException ex
    ) {
        return new ResponseEntity<>("단축 url 찾지 못함", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortenUrlKeyException.class)
    public ResponseEntity<String> handleLackOfShortenUrlKeyException(
            LackOfShortenUrlKeyException ex
    ) {
        return new ResponseEntity<>("url 부족", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
