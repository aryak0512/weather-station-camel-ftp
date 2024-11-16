package com.example.weather.dao;

import com.example.weather.entities.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
public class WeatherDao {

    private final JdbcTemplate jdbcTemplate;

    public WeatherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(List<Weather> weatherData) {

        String sql = """
                INSERT INTO weather_data (site_id, wind_direction, wind_speed, dry_bulb_temperature, dew_point, rf10m, recorded_at)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Weather weather = weatherData.get(i);
                    ps.setInt(1, weather.getSiteId());
                    ps.setObject(2, weather.getWindDirection(), Types.DOUBLE);
                    ps.setObject(3, weather.getWindSpeed(), java.sql.Types.DOUBLE);
                    ps.setObject(4, weather.getDryBulbTemperature(), java.sql.Types.DOUBLE);
                    ps.setObject(5, weather.getDewPoint(), java.sql.Types.DOUBLE);
                    ps.setObject(6, weather.getRf10m(), java.sql.Types.DOUBLE);
                    ps.setObject(7, weather.getRecordedAt(), java.sql.Types.TIMESTAMP);
                }

                @Override
                public int getBatchSize() {
                    return weatherData.size();
                }
            });

        } catch (Exception e) {
            log.error("Exception occurred : {}", e.getMessage());
            return false;
        }

        return true;

    }

    public List<Weather> getWeatherDataBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int siteId) {

        String sql = "SELECT * FROM weather_data WHERE site_id = ? and (recorded_at BETWEEN ? AND ? ) ORDER BY recorded_at";

        return jdbcTemplate.query(sql, new Object[]{siteId, startDate, endDate}, (rs, rowNum) -> {
            Weather weatherData = new Weather();
            weatherData.setSiteId(rs.getInt("site_id"));
            weatherData.setWindDirection(rs.getDouble("wind_direction"));
            weatherData.setRecordedAt(rs.getTimestamp("recorded_at").toLocalDateTime());
            weatherData.setDewPoint(rs.getDouble("dew_point"));
            weatherData.setDryBulbTemperature(rs.getDouble("dry_bulb_temperature"));
            weatherData.setWindSpeed(rs.getDouble("wind_speed"));
            return weatherData;
        });
    }

}
