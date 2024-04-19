package com.example.url.service;

import com.example.url.dto.ShortenUrlCreateRequestDto;
import com.example.url.dto.ShortenUrlCreateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SimpleShortenUrlServiceTest {

    @Autowired
    private SimpleShortenUrlService simpleShortenUrlService;

    @Test
    @DisplayName("URL을 단축한 후 단축된 URL 키로 조회하면 원래 URL 조회")
    void shortenUrlAddTest() {
        String exceptedOriginalUrl = "https://www.abc.co.kr/";
        ShortenUrlCreateRequestDto shortenUrlCreateRequestDto =
                new ShortenUrlCreateRequestDto(exceptedOriginalUrl);

        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto =
                simpleShortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto);
        String shortenUrlKey = shortenUrlCreateResponseDto.getShortenUrlKey();
        String originalUrl = simpleShortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey);
        assertEquals(originalUrl, exceptedOriginalUrl);
    }
}