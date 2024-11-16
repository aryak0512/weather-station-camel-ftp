CREATE TABLE weather_data (
    site_id INT NOT NULL,
    wind_direction DOUBLE,               -- Nullable Double (use NULL if no wind direction)
    wind_speed DOUBLE,                 -- Nullable Double (use NULL if no wind speed)
    dry_bulb_temperature DOUBLE,       -- Nullable Double (use NULL if no temperature data)
    dew_point DOUBLE,                  -- Nullable Double (use NULL if no dew point data)
    rf10m DOUBLE,                      -- Nullable Double (use NULL if no rf10m data)
    recorded_at DATETIME,              -- Nullable DATETIME (use NULL if no timestamp)
    PRIMARY KEY (site_id, recorded_at) -- Assuming site_id and recorded_at together form a unique key
);

CREATE TABLE sites (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

SELECT *
FROM weather_data
WHERE recorded_at BETWEEN '2024-11-15 00:00:00' AND '2024-11-15 00:01:00';
