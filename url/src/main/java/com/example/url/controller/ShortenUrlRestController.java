package com.example.url.controller;

import com.example.url.dto.ShortenUrlCreateRequestDto;
import com.example.url.dto.ShortenUrlCreateResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlRestController {

    @PostMapping("/short/url")
    public ResponseEntity<ShortenUrlCreateResponseDto> createShortenUrl(
            @Valid @RequestBody ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ) {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{shortenUrlKey}")
    public ResponseEntity<?> redirectShortenUrl() {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/short/url/{shortenUrlKey}")
    public ResponseEntity<?> getShortenUrlInformation() {
        return ResponseEntity.ok().body(null);
    }
}
