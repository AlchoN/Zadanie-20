package com.example.weather.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.weather.model.Geodata;
import com.example.weather.model.Weather;
import com.example.weather.repository.GeodataRepository;

@RestController("/location")
public class LocationController {

    @Autowired
    private GeodataRepository repository;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/weather")
    public Weather redirectRequestWeather(@RequestParam String name) {
        Geodata geodata = repository.findByName(name).get();
        String url = String.format("http://localhost:8082/?lat=%s&lon=%s", geodata.getLat(), geodata.getLon());
        return restTemplate.getForObject(url, Weather.class);
    }

    @GetMapping
    public Optional<Geodata> getLocation(@RequestParam String name) {
        return repository.findByName(name);
    }

    @PostMapping
    public Geodata save(@RequestBody Geodata geodata) {
        return repository.save(geodata);
    }

}
