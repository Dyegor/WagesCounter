package org.dima.counter.controller;

import org.dima.counter.entity.PaySlip;
import org.dima.counter.service.CounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CounterController.class)
public class controllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private CounterService counterService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAddWeekPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/counter/addWeek"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPaySlipsListPage() throws Exception {
        List<String> paySlipsList = Arrays.asList("10-11-2020", "11-11-2020", "12-11-2020");

        given(counterService.getPaySlipsList()).willReturn(paySlipsList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/counter/paySlipsList"))
                .andExpect(status().isOk());
    }

    @Test
    public void testYearlyReportsPage() throws Exception {
        PaySlip yearlyReport = new PaySlip();   

        given(counterService.getYearlyPayments()).willReturn(yearlyReport);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/counter/yearlyReport"))
                .andExpect(status().isOk());
    }
}
