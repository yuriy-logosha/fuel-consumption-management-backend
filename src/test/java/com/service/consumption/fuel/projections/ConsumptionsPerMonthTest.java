package com.service.consumption.fuel.projections;

import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ConsumptionsPerMonthTest {

    @Autowired
    private ConsumptionRepository repo;


    // list fuel consumption records for specified month (each row should contain: fuel type, volume, date, price, total price, driver ID)

    @Test
    public void consumptionsPerMonth1Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonths = repo.consumptionsPerMonth(1, 2010);
        assertEquals(5, consumptionsPerMonths.size());
    }

    @Test
    public void consumptionsPerMonth2Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonths = repo.consumptionsPerMonth(2, 2010);
        assertEquals(4, consumptionsPerMonths.size());
    }

    @Test
    public void consumptionsPerMonth3Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonths = repo.consumptionsPerMonth(3, 2010);
        assertEquals(4, consumptionsPerMonths.size());
    }

    @Test
    public void consumptionsPerMonth4Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonths = repo.consumptionsPerMonth(4, 2010);
        assertEquals(1, consumptionsPerMonths.size());
    }

    // January

    @Test
    public void consumptionsPerMonthWithDriver1Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(1, 2010, 1);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(57.75, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 1).getAmount());
        assertEquals(1.155, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 1).getPrice());
    }

    @Test
    public void consumptionsPerMonthWithDriver2Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(1, 2010, 2);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(50.2, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 2).getAmount());
        assertEquals(1.255, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 2).getPrice());
    }

    @Test
    public void consumptionsPerMonthWithDriver3Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(1, 2010, 3);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(121.95, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 3).getAmount());
        assertEquals(1.355, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 3).getPrice());
    }

    @Test
    public void consumptionsPerMonthWithDriver4Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(1, 2010, 4);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(130.95, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 4).getAmount());
        assertEquals(1.455, extractRow(consumptionsPerMonthWithDriver, 1, 2010, 4).getPrice());
    }

    // February

    @Test
    public void consumptionsPerMonth2WithDriver1Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(2, 2010, 1);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(74.205, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 1).getAmount());
        assertEquals(1.455, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 1).getPrice());
    }

    @Test
    public void consumptionsPerMonth2WithDriver2Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(2, 2010, 2);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(70.46, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 2).getAmount());
        assertEquals(1.355, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 2).getPrice());
    }

    @Test
    public void consumptionsPerMonth2WithDriver3Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(2, 2010, 3);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(66.515, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 3).getAmount());
        assertEquals(1.255, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 3).getPrice());
    }

    @Test
    public void consumptionsPerMonth2WithDriver4Test() {
        List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver = repo.consumptionsPerMonthWithDriver(2, 2010, 4);
        assertEquals(1, consumptionsPerMonthWithDriver.size());
        assertEquals(62.37, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 4).getAmount());
        assertEquals(1.155, extractRow(consumptionsPerMonthWithDriver, 2, 2010, 4).getPrice());
    }

    //Helpers

    private ConsumptionsPerMonth extractRow(List<ConsumptionsPerMonth[]> total, final int month, final int year, final int driver) {
        for (ConsumptionsPerMonth[] consumptionsPerMonth : total) {
            for (ConsumptionsPerMonth consumptionPerMonth : consumptionsPerMonth) {
                if ((consumptionPerMonth.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonthValue() == month &&
                        (consumptionPerMonth.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYear() == year &&
                        consumptionPerMonth.getDriver_id().equals(driver)) {
                    return consumptionPerMonth;
                }
            }
        }
        return null;
    }


}