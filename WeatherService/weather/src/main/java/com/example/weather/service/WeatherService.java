package com.example.weather.service;

import com.example.weather.model.Root;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "weatherCache", key = "#lat + '-' + #lon")
    public Root getWeather(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=6c260e98b561e4b0412baf1487de4fed";
        return restTemplate.getForObject(url, Root.class);
    }
}