package de.jfit.regiokonzept.tools.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;

@RunWith(SpringRunner.class)
@WebMvcTest(StartController.class)
@ActiveProfiles("test")
public class StartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToolsConfiguration toolsconfiguration;

    @Test
    public void testHomeController() throws Exception {

        when(toolsconfiguration.getWelcomeMessage()).thenReturn("Regiokonzept - Tools");

        mockMvc.perform(get("/regiokonzept/tools")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Regiokonzept - Tools")));
    }

}