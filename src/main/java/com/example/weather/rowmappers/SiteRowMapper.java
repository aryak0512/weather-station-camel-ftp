package com.example.weather.rowmappers;

import com.example.weather.entities.Site;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRowMapper implements RowMapper<Site> {

    @Override
    public Site mapRow(ResultSet rs, int rowNum) throws SQLException {

        int siteId = rs.getInt("id");
        String name = rs.getString("name");
        Site s = new Site();
        s.setName(name);
        s.setId(siteId);
        return s;
    }
}
