/*
 * StartController.java
 *
 * Created on 16.09.2018
 *
 * Copyright (C) 2018 Jan Furkert IT-Services, All rights reserved.
 */
package de.jfit.regiokonzept.tools.controllers.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;

/**
 * @author minion69
 */
@Controller
public class StartControllerImpl {

    private ToolsConfiguration toolsConfiguration;

    @Autowired
    public StartControllerImpl(final ToolsConfiguration toolsConfiguration) {
        super();
        this.toolsConfiguration = toolsConfiguration;
    }

    @GetMapping("/regiokonzept/greet")
    public String greet() {
        return "Hello world!";
    }

    @GetMapping("/regiokonzept/tools")
    public String start(Map<String, Object> model) {
        model.put("message", toolsConfiguration.getWelcomeMessage());

        // resources/templates/start.html
        return "start";
    }
}