package com.tutorial.SpringTutorial.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShedLockTableInitializer {

    private final JdbcTemplate jdbcTemplate;

    public ShedLockTableInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createTableIfMissing() {
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS shedlock (
                    name VARCHAR(64) NOT NULL,
                    lock_until TIMESTAMP(3) NULL,
                    locked_at TIMESTAMP(3) NULL,
                    locked_by VARCHAR(255) NULL,
                    PRIMARY KEY (name)
                )
                """);
    }
}

