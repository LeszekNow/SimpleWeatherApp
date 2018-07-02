package com.leszeknowinski.SimpleWeatherApp.controllers;

import com.leszeknowinski.SimpleWeatherApp.models.services.WeatherService;
import com.leszeknowinski.SimpleWeatherApp.models.weatherData.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class PrognosisController {


    private WeatherService weatherService;

    @Autowired
    public PrognosisController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/prognosis")
    public String prognosis() {
        return "prognosisInfo";
    }

    @PostMapping("/prognosis")
    public String prognosis(@RequestParam("city") String city, Model model) {
        model.addAttribute("dt", weatherService.callPrognosis(city).get(0).getDateTime());
        model.addAttribute("temp", convertTempIntoC(weatherService.callPrognosis(city).get(0).getGlobalStats().getTemperature()));
        model.addAttribute("cloud", weatherService.callPrognosis(city).get(0).getCloudStats().getCloudyPercent());
        model.addAttribute("press", weatherService.callPrognosis(city).get(0).getGlobalStats().getPressure());
        model.addAttribute("winds", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(0).getWindStats().getWindSpeed()));
        model.addAttribute("humid", weatherService.callPrognosis(city).get(0).getGlobalStats().getHumidity());
        model.addAttribute("dt2", weatherService.callPrognosis(city).get(1).getDateTime());
        model.addAttribute("temp2", convertTempIntoC(weatherService.callPrognosis(city).get(1).getGlobalStats().getTemperature()));
        model.addAttribute("cloud2", weatherService.callPrognosis(city).get(1).getCloudStats().getCloudyPercent());
        model.addAttribute("press2", weatherService.callPrognosis(city).get(1).getGlobalStats().getPressure());
        model.addAttribute("winds2", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(1).getWindStats().getWindSpeed()));
        model.addAttribute("humid2", weatherService.callPrognosis(city).get(1).getGlobalStats().getHumidity());
        model.addAttribute("dt3", weatherService.callPrognosis(city).get(2).getDateTime());
        model.addAttribute("temp3", convertTempIntoC(weatherService.callPrognosis(city).get(2).getGlobalStats().getTemperature()));
        model.addAttribute("cloud3", weatherService.callPrognosis(city).get(2).getCloudStats().getCloudyPercent());
        model.addAttribute("press3", weatherService.callPrognosis(city).get(2).getGlobalStats().getPressure());
        model.addAttribute("winds3", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(2).getWindStats().getWindSpeed()));
        model.addAttribute("humid3", weatherService.callPrognosis(city).get(2).getGlobalStats().getHumidity());
        model.addAttribute("dt4", weatherService.callPrognosis(city).get(3).getDateTime());
        model.addAttribute("temp4", convertTempIntoC(weatherService.callPrognosis(city).get(3).getGlobalStats().getTemperature()));
        model.addAttribute("cloud4", weatherService.callPrognosis(city).get(3).getCloudStats().getCloudyPercent());
        model.addAttribute("press4", weatherService.callPrognosis(city).get(3).getGlobalStats().getPressure());
        model.addAttribute("winds4", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(3).getWindStats().getWindSpeed()));
        model.addAttribute("humid4", weatherService.callPrognosis(city).get(3).getGlobalStats().getHumidity());
        model.addAttribute("dt5", weatherService.callPrognosis(city).get(4).getDateTime());
        model.addAttribute("temp5", convertTempIntoC(weatherService.callPrognosis(city).get(4).getGlobalStats().getTemperature()));
        model.addAttribute("cloud5", weatherService.callPrognosis(city).get(4).getCloudStats().getCloudyPercent());
        model.addAttribute("press5", weatherService.callPrognosis(city).get(4).getGlobalStats().getPressure());
        model.addAttribute("winds5", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(4).getWindStats().getWindSpeed()));
        model.addAttribute("humid5", weatherService.callPrognosis(city).get(4).getGlobalStats().getHumidity());
        return "prognosisInfo";
    }

    public String convertDateTimeFromMS(long msec) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
        msec = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(msec);
        return formatter.format(calendar.getTime());
    }

    public float convertTempIntoC(float tempInK) {
        return tempInK - (float) 273.15;
    }

    public float convertSpeedIntoKmh(float speedInMs) {
        return speedInMs * (float) 3.6;
    }
}
