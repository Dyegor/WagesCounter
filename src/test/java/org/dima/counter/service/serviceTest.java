package org.dima.counter.service;

import org.dima.counter.dao.CounterDao;
import org.dima.counter.entity.PaySlip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class serviceTest {

    @Autowired
    private CounterService counterService;

    @MockBean
    private CounterDao counterDao;

    @Test
    public void getPaySlipsList() {
        when(counterDao.getPaySlipsList()).thenReturn(Stream.of("15-11-2020", "08-11-2020", "01-11-2020").collect(Collectors.toList()));
        assertEquals(3, counterService.getPaySlipsList().size());
    }

    @Test
    public void getPaySlipByDate() {
        String date = "15-11-2020";
        PaySlip paySlip = new PaySlip();
        when(counterDao.getPaySlipByDate(date)).thenReturn(paySlip);
        assertEquals(paySlip, counterService.getPaySlipByDate(date));
    }

    @Test
    public void deleteWeeklyData() {
        String date = "15-11-2020";
        counterDao.deletePaySlip(date);
        verify(counterDao, times(1)).deletePaySlip(date);
    }
}
