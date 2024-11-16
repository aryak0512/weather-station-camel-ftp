package com.example.weather.processor;

import com.example.weather.entities.Weather;
import com.example.weather.service.WeatherService;
import com.example.weather.utils.DateUtils;
import com.example.weather.utils.FilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author aryak
 * This bean is responsible for entire processing of the file post download from FTP server
 */
@Slf4j
@Component
public class FileProcessor {

    private final WeatherService weatherService;

    public FileProcessor(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * reads and processes the weather data file
     *
     * @param fileContent string
     * @param fileName    name of the file in YYYYMMDDHHmm format
     */
    public void processFile(String fileContent, String fileName) {

        // TODO : add condition for duplicate file check (optional)

        List<Weather> weatherDataList = getWeatherDataList(fileContent, fileName);
        boolean success = weatherService.batchInsert(weatherDataList);
        if ( ! success ) {
            log.info("Some records for file : {} failed to push to DB successfully.", fileName);
        }

    }

    /**
     * reads the file content and returns serialised object list
     *
     * @param fileContent content of file downloaded from FTP server
     * @param fileName    name of .axf file
     * @return List<Weather>
     */
    public List<Weather> getWeatherDataList(String fileContent, String fileName) {

        LocalDateTime recordedAt = DateUtils.getFileDateTime(fileName);
        List<Weather> dataList = new LinkedList<>();
        String[] lines = fileContent.split("\n");

        // skip first two lines since they are labels
        for ( int i = 2; i < lines.length; i++ ) {
            String[] fields = lines[i].split(",");
            if ( fields.length > 40 ) {
                Weather weatherData = getWeatherData(lines[i]);
                if ( weatherData != null ) {
                    weatherData.setRecordedAt(recordedAt);
                    dataList.add(weatherData);
                }
            }
        }

        log.info("File : {} contains {} lines but {} lines were processed .", fileName, lines.length, dataList.size());
        return dataList;
    }


    /**
     * Prepare the weather data object
     *
     * @param line from the weather data file
     * @return the weather data object
     */
    private static Weather getWeatherData(String line) {

        String[] fields = line.split(",");

        // extract params
        String idNum = fields[0].trim();                // file 1  , idx : 0
        String windDirection = fields[10].trim();       // file 11 , idx : 10
        String windSpeed = fields[11].trim();           // file 12 , idx : 11
        String dryBulbTemperature = fields[21].trim();  // file 22 , idx : 21
        String dewPoint = fields[22].trim();            // file 23 , idx : 22
        //String rf10m = fields[12].trim();

        // include only if site ID is present in master cache
        if ( FilterService.ids.contains(Integer.parseInt(idNum)) ) {

            Weather weatherData = new Weather();
            weatherData.setSiteId(Integer.parseInt(idNum));
            weatherData.setWindDirection(!windDirection.contains("-999") ? Double.parseDouble(windDirection) : null);
            weatherData.setWindSpeed(!windSpeed.contains("-999") ? Double.parseDouble(windSpeed) : null);
            weatherData.setDryBulbTemperature(!dryBulbTemperature.contains("-999") ? Double.parseDouble(dryBulbTemperature) : null);
            weatherData.setDewPoint(!dewPoint.contains("-999") ? Double.parseDouble(dewPoint) : null);
            //weatherData.setRf10m(!rf10m.contains("-999") ? Double.parseDouble(rf10m) : null);
            return weatherData;
        }

        return null;
    }

}

