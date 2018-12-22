/*
 * StartProcessTest.java
 *
 * Created on 22 Dec 2018
 *
 * Copyright (C) 2018 Volkswagen AG, All rights reserved.
 */
package de.jfit.regiokonzept.tools.process.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.jfit.regiokonzept.tools.process.StartProcess;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class StartProcessTest {

    @Autowired
    private StartProcess startProcess;

    @Test
    public void retrieveMessage() {
        assertEquals("Regiokonzept - Tools", startProcess.retrieveMessage());
    }
}
