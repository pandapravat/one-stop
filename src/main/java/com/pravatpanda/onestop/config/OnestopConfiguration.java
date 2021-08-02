/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class OnestopConfiguration {

    public JdbcTemplate template(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
