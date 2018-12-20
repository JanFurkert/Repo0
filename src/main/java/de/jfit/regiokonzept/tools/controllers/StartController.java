/*
 * StartController.java
 *
 * Created on 16.09.2018
 *
 * Copyright (C) 2018 Jan Furkert IT-Services, All rights reserved.
 */
package de.jfit.regiokonzept.tools.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;

/**
 * @author minion69
 */
@Controller
public class StartController {

    private ToolsConfiguration toolsConfiguration;

    @Autowired
    public StartController(final ToolsConfiguration toolsConfiguration) {
        super();
        this.toolsConfiguration = toolsConfiguration;
    }

    @GetMapping("/regiokonzept/tools")
    public String start(Map<String, Object> model) {
        model.put("message", toolsConfiguration.getWelcomeMessage());
        return "start";
    }
}
