package com.example.url.service;

import com.example.url.domain.ShortenUrl;
import com.example.url.dto.ShortenUrlCreateRequestDto;
import com.example.url.dto.ShortenUrlCreateResponseDto;
import com.example.url.dto.ShortenUrlInformationDto;
import com.example.url.repository.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleShortenUrlService {
    private final ShortenUrlRepository shortenUrlRepository;

    @Autowired
    SimpleShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }
    public ShortenUrlCreateResponseDto generateShortenUrl(
            ShortenUrlCreateRequestDto shortenUrlCreateRequestDto
    ) {
        String originalUrl = shortenUrlCreateRequestDto.getOriginalUrl();
        String shortenUrlKey = ShortenUrl.generateShortenUrlKey();

        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        ShortenUrlCreateResponseDto shortenUrlCreateResponseDto = new ShortenUrlCreateResponseDto(shortenUrl);
        return shortenUrlCreateResponseDto;
    }

    public ShortenUrlInformationDto getShortenUrlInformationByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);
        ShortenUrlInformationDto shortenUrlInformationDto = new ShortenUrlInformationDto(shortenUrl);
        return shortenUrlInformationDto;
    }

    public String getOriginalUrlByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findShortenUrlByShortenUrlKey(shortenUrlKey);

        shortenUrl.increaseRedirectCount();
        shortenUrlRepository.saveShortenUrl(shortenUrl);

        String originalUrl = shortenUrl.getOriginalUrl();
        return originalUrl;
    }
}
