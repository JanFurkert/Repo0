/*
 * StartController.java
 *
 * Created on 16.09.2018
 *
 * Copyright (C) 2018 Jan Furkert IT-Services, All rights reserved.
 */
package de.jfit.regiokonzept.tools.controllers.impl;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;
import de.jfit.regiokonzept.tools.form.BiotopTypForm;
import de.jfit.regiokonzept.tools.process.StartProcess;

/**
 * @author minion69
 */
@Controller
public class StartControllerImpl {

    private StartProcess startProcess;
    private ToolsConfiguration toolsConfiguration;

    @Autowired
    public StartControllerImpl(final ToolsConfiguration toolsConfiguration, final StartProcess startProcess) {
        super();
        this.startProcess = startProcess;
        this.toolsConfiguration = toolsConfiguration;
    }

    @GetMapping("/greet")
    String greet() {
        return "Hello world!";
    }

    @GetMapping("/homeNotSignedIn")
    String homeNotSignedIn() {
        return "home/homeNotSignedIn";
    }

    @GetMapping("/homeSignedIn")
    String homeSignedIn(final Model model) {
        BiotopTypForm biotopTypForm = new BiotopTypForm();
        biotopTypForm.setProjectName("Stromtrasse Kelsterbach - Rödermark");
        biotopTypForm.setNumber(new Integer(1));
        model.addAttribute("biotopTypForm", biotopTypForm);

        return "home/homeSignedIn";
    }

    @GetMapping("/")
    String start(Principal principal) {

        // TODO: Security
        // resources/templates/homeSignedIn.html bzw. homeNotSignedIn.html
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/start")
    String start(final Map<String, Object> model) {
        model.put("message", startProcess.retrieveMessage());

        // resources/templates/start.html
        return "start";
    }
}
