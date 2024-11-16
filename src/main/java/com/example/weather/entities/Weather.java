package com.example.weather.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Weather {

    private int siteId;
    private Double windDirection;
    private Double windSpeed;
    private Double dryBulbTemperature;
    private Double dewPoint;
    private Double rf10m;
    private LocalDateTime recordedAt;
}
