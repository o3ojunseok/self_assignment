package com.example.url.domain;

import java.util.Random;

public class ShortenUrl {
    private String originalUrl;
    private String shorterUrlKey;
    private Long redirectCount;

    public ShortenUrl(String originalUrl, String shorterUrlKey) {
        this.originalUrl = originalUrl;
        this.shorterUrlKey = shorterUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShorterUrlKey() {
        return shorterUrlKey;
    }

    public Long getRedirectCount() {
        return redirectCount;
    }

    public static String generateShortenUrlKey() {
        String base56Characters = "23456789ABCDEFGHIJKLMNOPQUSTUVWXYZabcdefghijklmnopqustuvwxyz";
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for (int count = 0; count < 8; count++) {
            int base56CharactersIndex = random.nextInt(0, base56Characters.length());
            char base56Character = base56Characters.charAt(base56CharactersIndex);
            shortenUrlKey.append(base56Character);
        }
        return shortenUrlKey.toString();
    }
}
