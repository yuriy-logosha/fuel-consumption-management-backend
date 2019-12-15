package com.service.consumption.fuel.projections;

import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class StatisticsByMonthTest {

    @Autowired
    private ConsumptionRepository repo;

    //statistics for each month, list fuel consumption records grouped by fuel type (each row should contain: fuel type, volume, average price, total price)
    @Test
    public void statisticsForMonthTest() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonth(2010);
        assertEquals(8, total.size());
        assertEquals(180,
                extractRow(total, 1, "D").getSum_liters());
        assertEquals(76.63333333333334,
                extractRow(total, 1, "D").getAverage_price());
        assertEquals(156,
                extractRow(total, 2, "D").getSum_liters());
        assertEquals(70.39333333333333,
                extractRow(total, 2, "D").getAverage_price());
        assertEquals(51,
                extractRow(total, 3, "D").getSum_liters());
        assertEquals(64.005,
                extractRow(total, 3, "D").getAverage_price());
        assertEquals(51,
                extractRow(total, 4, "D").getSum_liters());
        assertEquals(64.005,
                extractRow(total, 4, "D").getAverage_price());
        assertEquals(180,
                extractRow(total, 1, "E98").getSum_liters());
        assertEquals(135.45,
                extractRow(total, 1, "E98").getAverage_price());
        assertEquals(54,
                extractRow(total, 2, "E98").getSum_liters());
        assertEquals(62.37,
                extractRow(total, 2, "E98").getAverage_price());
        assertEquals(51,
                extractRow(total, 3, "E98").getSum_liters());
        assertEquals(74.205,
                extractRow(total, 3, "E98").getAverage_price());
        assertEquals(102,
                extractRow(total, 3, "E95").getSum_liters());
        assertEquals(66.555,
                extractRow(total, 3, "E95").getAverage_price());
    }

    @Test
    public void statisticsForMonthWithDriver1Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 1);
        assertEquals(4, total.size());
    }

    @Test
    public void statisticsForMonthWithDriver2Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 2);
        assertEquals(2, total.size());
    }

    @Test
    public void statisticsForMonthWithDriver3Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 3);
        assertEquals(2, total.size());
    }

    @Test
    public void statisticsForMonthWithDriver4Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 4);
        assertEquals(4, total.size());
    }

    @Test
    public void statisticsForMonthWithDriver5Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 5);
        assertEquals(1, total.size());
    }

    @Test // Test for not existing driver
    public void statisticsForMonthWithDriver6Test() {
        List<StatisticsByMonth[]> total = repo.statisticsForMonthWithDriver(2010, 6);
        assertEquals(0, total.size());
    }

    //Helpers

    private StatisticsByMonth extractRow(List<StatisticsByMonth[]> total, final Integer month, final String type) {
        for (StatisticsByMonth[] statisticsByMonth : total) {
            for (StatisticsByMonth statisticByMonth : statisticsByMonth) {
                if (month.equals(statisticByMonth.getMonth()) && type.equalsIgnoreCase(statisticByMonth.getType().toString())) {
                    return statisticByMonth;
                }
            }
        }
        return null;
    }

}