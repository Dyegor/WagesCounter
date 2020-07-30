package controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.aspectj.lang.annotation.Before;
import org.dima.counter.controller.CounterController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class controllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new CounterController()).build();
    }

    @Test
    public void testSayHelloWorld() throws Exception {
        this.mockMvc.perform(get("/success").andExpect(status().isOk()));
    }
}
