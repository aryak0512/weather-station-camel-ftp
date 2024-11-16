
# AWS — Automatic Weather Station

You are building a local repository of basic weather data, including temperature, rainfall, and
wind related data, which may have various business uses. As a proof of concept, you are
required to build components performing the following:

- A database which models 
a) simple site-based information (in particular site name,
location, and ID), and 

b) raw weather data for each site where available; as well as
any additional information you require in your implementation


- A backend service which downloads and interprets raw weather information from the
Bureau of Meteorology and delivers them to a database

- A simple website which allows users to interrogate the database, using drop-downs,
time-windows for data availability, and tables on a webpage, to show weather
information about a particular site over a period of time

The raw weather information from the Bureau of Meteorology is the IDY03100 product,
which is ‘Real-Time AWS Obsevations - Australia Region (all obs for 15 minutes)’. This data
consists of 15 minute readings, and is made available from time to time over the course of
the hour; it is available on BoM’s FTP site, and is available for 72 hours from initial upload

## Pre-requisites
- IntelliJ
- MySQL 
- JDK 21
- Any modern browser
- Weather stations text file [see important resources]

## Important resources
- http://www.bom.gov.au/climate/data/lists_by_element/stations.txt — a list of weather
stations. You only need to use the weather stations with a ‘WMO’ field, and these can
be matched against the ‘ID_num’ field in IDY03100 payloads
- http://www.bom.gov.au/weather-services/about/IDY03100.doc — a description of the
IDY03100 file format

## Running project locally

Clone the project

```bash
  git clone https://github.com/aryak0512/weather-station-camel-ftp.git
```

Go to the project directory

```bash
  cd weather-station
```

Start the server. Right click -> Run as Java Application. Server is ready to accept requests on port 8080.

## Deployment

To build this project run
```bash
  mvn clean install -DskipTests=true
```
JAR file gets created in target folder

To run the project
```bash
  java -jar target/weather-station-0.0.1-SNAPSHOT.jar
```
