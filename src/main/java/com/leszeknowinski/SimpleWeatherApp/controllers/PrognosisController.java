package com.leszeknowinski.SimpleWeatherApp.controllers;

import com.leszeknowinski.SimpleWeatherApp.models.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* todo currently weather prognosis is fetched hourly and we need fetched it daily...  */

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
        for(int i = 0; i < 5; i++) {
            model.addAttribute("dt"+(i+1)+"", weatherService.callPrognosis(city).get(i).getDateTime());
            model.addAttribute("temp"+(i+1)+"", convertTempIntoC(weatherService.callPrognosis(city).get(i).getGlobalStats().getTemperature()));
            model.addAttribute("cloud"+(i+1)+"", weatherService.callPrognosis(city).get(i).getCloudStats().getCloudyPercent());
            model.addAttribute("press"+(i+1)+"", weatherService.callPrognosis(city).get(i).getGlobalStats().getPressure());
            model.addAttribute("winds"+(i+1)+"", convertSpeedIntoKmh(weatherService.callPrognosis(city).get(i).getWindStats().getWindSpeed()));
            model.addAttribute("humid"+(i+1)+"", weatherService.callPrognosis(city).get(i).getGlobalStats().getHumidity());
        }
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
