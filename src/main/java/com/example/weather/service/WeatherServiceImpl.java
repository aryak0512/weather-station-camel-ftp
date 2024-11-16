package com.example.weather.service;

import com.example.weather.dao.WeatherDao;
import com.example.weather.entities.Weather;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherServiceImpl implements  WeatherService {

    private final WeatherDao weatherDao;

    public WeatherServiceImpl(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Override
    public boolean batchInsert(List<Weather> weathers) {
        return weatherDao.save(weathers);
    }

    @Override
    public List<Weather> getWeatherDataBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int siteId) {
        return weatherDao.getWeatherDataBetweenDates(startDate, endDate, siteId);
    }
}
