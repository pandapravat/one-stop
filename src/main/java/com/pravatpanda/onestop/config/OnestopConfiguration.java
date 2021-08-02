/*
 * Copyright (c) 2021. Pravat Panda
 * All rights reserved
 */

package com.pravatpanda.onestop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * The type Onestop configuration.
 */
@Configuration
public class OnestopConfiguration {

    /**
     * Template jdbc template.
     *
     * @param ds the ds
     * @return the jdbc template
     */
    public JdbcTemplate template(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
