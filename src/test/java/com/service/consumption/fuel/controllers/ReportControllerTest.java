package com.service.consumption.fuel.controllers;

import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class ReportControllerTest {
    private final Integer month = 3;
    private final Integer year = 2010;
    private final String yearStr = "year="+year;
    private final Integer driver = 9999;
    private final String driverStr = "driver="+driver;

    private final String totalSpentAmountByMonth = "/totalSpentAmountByMonth";
    private final String consumptionsByMonth = "/consumptionsByMonth";
    private final String statisticsForMonth = "/statisticsForMonth";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumptionRepository mockRepository;

    @Test
    public void totalSpentAmountByMonthIsOk() throws Exception {
        mockMvc.perform(get(totalSpentAmountByMonth+"?"+yearStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).totalSpentAmountByMonth(year);
    }

    @Test
    public void totalSpentAmountByMonthWithDriverIsOk() throws Exception {
        mockMvc.perform(get(totalSpentAmountByMonth+"?"+yearStr+"&"+driverStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).totalSpentAmountByMonthWithDriver(year, driver);
    }

    @Test
    public void totalSpentAmountByMonthIsBadYearLessThan1970() throws Exception {
        mockMvc.perform(get(totalSpentAmountByMonth+"?year=1969")).andExpect(status().isBadRequest());
        verify(mockRepository, times(0)).totalSpentAmountByMonth(anyInt());
    }

    @Test
    public void totalSpentAmountByMonthIsBadYearIsEmpty() throws Exception {
        mockMvc.perform(get(totalSpentAmountByMonth+"?year=")).andExpect(status().isBadRequest());
        verify(mockRepository, times(0)).totalSpentAmountByMonth(anyInt());
    }

    @Test
    public void totalSpentAmountByMonthIsBadYearNegative() throws Exception {
        mockMvc.perform(get(totalSpentAmountByMonth+"?year=-"+year)).andExpect(status().isBadRequest());
        verify(mockRepository, times(0)).totalSpentAmountByMonth(anyInt());
    }

    @Test
    public void consumptionsByMonth() throws Exception {
        mockMvc.perform(get(consumptionsByMonth+"?month="+month+"&"+yearStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).consumptionsPerMonth(month, year);
    }

    @Test
    public void consumptionsByMonthIsBadInvalidMonth() throws Exception {
        mockMvc.perform(get(consumptionsByMonth+"?month=13&"+yearStr)).andExpect(status().isBadRequest());
        verify(mockRepository, times(0)).consumptionsPerMonth(month, year);
    }

    @Test
    public void consumptionsByMonthWithDriver() throws Exception {
        mockMvc.perform(get(consumptionsByMonth+"?month="+month+"&"+yearStr+"&"+driverStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).consumptionsPerMonthWithDriver(month, year, driver);
    }

    @Test
    public void statisticsForMonth() throws Exception {
        mockMvc.perform(get(statisticsForMonth+"?"+yearStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).statisticsForMonth(year);
    }

    @Test
    public void statisticsForMonthWithDriver() throws Exception {
        mockMvc.perform(get(statisticsForMonth+"?"+yearStr+"&"+driverStr)).andExpect(status().isOk());
        verify(mockRepository, times(1)).statisticsForMonthWithDriver(year, driver);
    }
}