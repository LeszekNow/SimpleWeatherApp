package com.leszeknowinski.SimpleWeatherApp.controllers;

import com.leszeknowinski.SimpleWeatherApp.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

/* todo improve and develop app in terms of frontend, */

@Controller
public class CurrentWeatherController {

    // @Autowired
    // WeatherService weatherService

    private WeatherService weatherService;

    @Autowired
    public CurrentWeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/")
    public String index() {
        return "weatherInfo";
    }

    @PostMapping("/")
    public String index(@RequestParam("city") String city, Model model) {
        model.addAttribute("temp", convertTempIntoC(weatherService.makeCall(city).getGlobalStats().getTemperature()));
        model.addAttribute("cloudiness", weatherService.makeCall(city).getCloudStats().getCloudyPercent());
        model.addAttribute("pressure", weatherService.makeCall(city).getGlobalStats().getPressure());
        model.addAttribute("wind", convertSpeedIntoKmh(weatherService.makeCall(city).getWindStats().getWindSpeed()));
        model.addAttribute("humidity", weatherService.makeCall(city).getGlobalStats().getHumidity());

        return "weatherInfo";
    }

    public float convertTempIntoC(float tempInK) {
        return tempInK - (float) 273.15;
    }

    public float convertSpeedIntoKmh(float speedInMs) {
        return speedInMs * (float) 3.6;
    }

}

