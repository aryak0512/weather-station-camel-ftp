package com.example.weather.service;

import com.example.weather.entities.Weather;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherService {

    boolean batchInsert(List<Weather> weathers);

    List<Weather> getWeatherDataBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int siteId);
}
