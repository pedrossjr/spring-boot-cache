package io.github.pedrossjr.cache.controllers;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    static final String cacheName = "cache-produto";
    static final String cacheKey = "1234567890";

    @GetMapping("/clear")
    @CacheEvict(value = cacheName, key = cacheKey)
    public String clearCache() {
        return "Limpou o cache de produtos!";
    }

}
