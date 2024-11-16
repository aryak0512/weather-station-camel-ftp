package com.example.weather.dao;

import com.example.weather.entities.Site;
import com.example.weather.rowmappers.SiteRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Component
public class SiteDao {

    private final JdbcTemplate jdbcTemplate;

    public SiteDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(List<Site> sites) {

        String sql = """
                INSERT INTO sites (id, name)
                VALUES (?, ?)
                """;

        try {

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Site site = sites.get(i);
                    ps.setInt(1, site.getId());
                    ps.setObject(2, site.getName(), java.sql.Types.VARCHAR);
                }

                @Override
                public int getBatchSize() {
                    return sites.size();
                }
            });

        } catch (Exception e) {
            log.error("Exception occurred : {}", e.getMessage());
            return false;
        }

        return true;

    }

    public List<Site> getAllSites() {
        String sql = "select * from sites";
        return jdbcTemplate.query(sql, new SiteRowMapper());
    }
}
