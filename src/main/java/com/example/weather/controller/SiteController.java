package com.example.weather.controller;

import com.example.weather.dao.SiteDao;
import com.example.weather.entities.Site;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class SiteController {

    private final SiteDao siteDao;

    public SiteController(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @GetMapping("/sites")
    public List<Site> getAllSites() {
        return siteDao.getAllSites();
    }
}
