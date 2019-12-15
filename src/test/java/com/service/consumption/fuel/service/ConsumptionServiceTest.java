package com.service.consumption.fuel.service;

import com.service.consumption.fuel.model.Consumption;
import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsumptionServiceTest {

    @Autowired
    private ConsumptionService service;

    @MockBean
    private ConsumptionRepository repo;

    @Test
    public void saveConsumptionsTest() throws ParseException {
        List<Consumption> collection = Arrays.asList(new Consumption[]{buildConsumption()});
        service.saveConsumptions(collection);
        verify(repo, times(1)).saveAll(collection);
    }

    //Helper
    private Consumption buildConsumption() throws ParseException {
        return new Consumption(1, (new SimpleDateFormat("dd.MM.yyyy")).parse("12.11.2010"), 10, "E95", 1.001);
    }

}