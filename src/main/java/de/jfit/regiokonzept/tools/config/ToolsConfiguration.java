/*
 * ToolsConfiguration.java
 *
 * Created on 17.12.2018
 *
 * Copyright (C) 2018 Volkswagen AG, All rights reserved.
 */
package de.jfit.regiokonzept.tools.config;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("regiokonzept")
public class ToolsConfiguration {

    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());

    private BuildProperties buildProperties;

    // @Value("${environment}")
    private String environment;

    // @Value("${welcome.message}")
    private String welcomeMessage;

    @Autowired
    public ToolsConfiguration(final BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public String getBuildName() {
        return buildProperties.getName();
    }

    public Instant getBuildTime() {
        return buildProperties.getTime();
    }

    public String getBuildTimeAsString() {
        return DATE_TIME_FORMATTER.format(getBuildTime());
    }

    public String getEnvironment() {
        return environment;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

}
