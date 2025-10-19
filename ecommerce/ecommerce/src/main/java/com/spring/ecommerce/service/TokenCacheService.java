package com.spring.ecommerce.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenCacheService {

    // On logout, we "blacklist" the token by adding it to the cache.
    @CachePut(value = "jwtTokens", key = "#token")
    public String blacklistToken(String token) {
        return "blacklisted";
    }

    // Before validating, we check if the token exists in the cache.
    @Cacheable(value = "jwtTokens", key = "#token")
    public String getTokenStatus(String token) {
        // If it's not in the cache, it's considered valid (not blacklisted).
        return null;
    }

    public boolean isTokenBlacklisted(String token) {
        return getTokenStatus(token) != null;
    }
}
