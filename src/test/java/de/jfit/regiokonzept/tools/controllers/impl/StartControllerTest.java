package de.jfit.regiokonzept.tools.controllers.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;
import de.jfit.regiokonzept.tools.process.StartProcess;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StartControllerImpl.class)
@ActiveProfiles("test")
public class StartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartProcess startProcess;

    @MockBean
    private ToolsConfiguration toolsconfiguration;

    @Test
    public void greet() throws Exception {
        assertThrows(NestedServletException.class, () -> mockMvc.perform(get("/regiokonzept/greet")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Hello World!"))));
    }

    @Test
    public void start() throws Exception {
        when(startProcess.retrieveMessage()).thenReturn("Regiokonzept - Tools");

        mockMvc.perform(get("/regiokonzept/tools")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Regiokonzept - Tools")));
    }
}