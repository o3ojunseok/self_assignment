package com.example.url.repository;

import com.example.url.domain.ShortenUrl;

public interface ShortenUrlRepository {
    void saveShortenUrl(ShortenUrl shortenUrl);

    ShortenUrl findShortenUrlByShortenUrlKey(String shortenUrlKey);
}
