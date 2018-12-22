/*
 * ToolsConfigurationTest.java
 *
 * Created on 17.12.2018
 *
 * Copyright (C) 2018 Volkswagen AG, All rights reserved.
 */
package de.jfit.regiokonzept.tools.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ToolsConfigurationTest {

    @Autowired
    private ToolsConfiguration toolsConfiguration;

    @Test
    public void buildName() {
        assertEquals("Regiokonzept: Tools", toolsConfiguration.getBuildName());
    }

    @Test
    public void buildTime() {
        assertNotNull(toolsConfiguration.getBuildTime());
    }

    @Test
    public void buildTimeAsString() {
        String[] timestamp = ToolsConfiguration.DATE_TIME_FORMATTER.format(new Date().toInstant()).split(" ");
        assertTrue(toolsConfiguration.getBuildTimeAsString().indexOf(timestamp[0]) > -1);
    }

    @Test
    public void environment() {
        assertEquals("development", toolsConfiguration.getEnvironment());
    }

    @Test
    public void welcomeMessage() {
        assertEquals("Regiokonzept - Tools", toolsConfiguration.getWelcomeMessage());
    }
}
