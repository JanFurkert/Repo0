/*
 * StartProcessImpl.java
 *
 * Created on 22 Dec 2018
 *
 * Copyright (C) 2018 Volkswagen AG, All rights reserved.
 */
package de.jfit.regiokonzept.tools.process.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;
import de.jfit.regiokonzept.tools.process.StartProcess;

@Service
public class StartProcessImpl implements StartProcess {

    private ToolsConfiguration toolsConfiguration;

    @Autowired
    public StartProcessImpl(final ToolsConfiguration toolsConfiguration) {
        super();
        this.toolsConfiguration = toolsConfiguration;
    }

    @Override
    public String retrieveMessage() {
        return toolsConfiguration.getWelcomeMessage();
    }

}
