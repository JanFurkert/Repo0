package de.jfit.regiokonzept.tools.controllers.impl;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;
import de.jfit.regiokonzept.tools.process.StartProcess;

@RunWith(SpringRunner.class)
@WebMvcTest(StartControllerImpl.class)
@ActiveProfiles("test")
public class StartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartProcess startProcess;

    @MockBean
    private ToolsConfiguration toolsconfiguration;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void greet() throws Exception {

        // JUnit 5
        // assertThrows(TemplateInputException.class, () ->
        // mockMvc.perform(get("/regiokonzept/greet")).andDo(print()).andExpect(status().isOk())
        // .andExpect(content().string(containsString("Hello World!"))));

        // JUnit 4
        thrown.expect(NestedServletException.class /* TemplateInputException.class */);
        mockMvc.perform(get("/regiokonzept/greet")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World!")));
    }

    @Test
    public void start() throws Exception {

        when(startProcess.retrieveMessage()).thenReturn("Regiokonzept - Tools");

        mockMvc.perform(get("/regiokonzept/tools")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Regiokonzept - Tools")));
    }

}