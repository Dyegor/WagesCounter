package org.dima.counter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dima.counter.entity.DailyReport;
import org.dima.counter.entity.PaySlip;
import org.dima.counter.entity.WeeklyTimeSheet;
import org.dima.counter.service.CounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class controllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private WeeklyTimeSheet weeklyTimeSheet;
    private DailyReport dailyReport;


    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private CounterService counterService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        objectMapper = new ObjectMapper();
        weeklyTimeSheet = new WeeklyTimeSheet();
        weeklyTimeSheet.setNormalHours(45);
        weeklyTimeSheet.setOverTimeHours(10);
        weeklyTimeSheet.setWeekEndingDate("22-11-2020");
        weeklyTimeSheet.setHourlyRate(25);
    }

    @Test
    public void testAddWeekPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/counter/addWeek"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled("for demonstration purposes")
    public void testAddingWeeklyData() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/counter/addingWeeklyData").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(weeklyTimeSheet))).andExpect(status().isOk()).andReturn();

        WeeklyTimeSheet response = objectMapper.readValue(result.getResponse().getContentAsString(), WeeklyTimeSheet.class);
        assertEquals(weeklyTimeSheet, response);
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
