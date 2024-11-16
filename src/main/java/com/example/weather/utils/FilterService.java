package com.example.weather.utils;

import com.example.weather.dao.SiteDao;
import com.example.weather.entities.Site;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class FilterService {

    @Value("${populate.cache.in.db}")
    boolean persist;

    private final SiteDao siteDao;

    public FilterService(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    public static final Set<Integer> ids = new HashSet<>();

    /**
     * @apiNote extract name and ID of sites from text file and populate master cache for lookups.
     * initial delay should be minimum, else camel routes will start without populate() completing
     */
    public void populate() {

        List<Site> sites = new LinkedList<>();
        String filePath = "/Users/aryak/Desktop/stations.txt";
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath)) ) {
            String line;
            while ( (line = reader.readLine()) != null ) {

                if ( !line.endsWith("..") ) {

                    try {

                        String siteName = line.split("\\s{2,}")[1].trim();
                        int siteId = Integer.parseInt(StringUtils.right(line, 5));

                        if ( persist ) {
                            Site site = new Site();
                            site.setId(siteId);
                            site.setName(siteName);
                            sites.add(site);
                        }

                        ids.add(siteId);

                    } catch (Exception e) {
                        log.error("Exception while extracting ID/name of site during startup for line : {} ", line);
                    }
                }
            }

            log.info("Found {} ids ", ids.size());

            if ( persist ) {
                siteDao.save(sites);
            }

        } catch (IOException e) {
            log.error("Exception occurred in populate method : ", e);
        }
    }
}
