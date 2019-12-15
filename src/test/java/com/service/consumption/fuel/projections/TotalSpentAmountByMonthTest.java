package com.service.consumption.fuel.projections;

import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class TotalSpentAmountByMonthTest {

    @Autowired
    private ConsumptionRepository repo;

    //total spent amount of money grouped by month
    @Test
    void totalSpentAmountByMonthTest() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonth(2010);

        assertEquals(4, total.size());
        assertEquals(500.8, extractAmount(total, 1));
        assertEquals(273.55, extractAmount(total, 2));
        assertEquals(64.005, extractAmount(total, 4));
        assertEquals(271.32, extractAmount(total, 3));
    }

    @Test
    void totalSpentAmountByMonthForDriver1Test() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonthWithDriver(2010, 1);
        assertEquals(4, total.size());
        assertEquals(57.75, extractAmount(total, 1));
        assertEquals(74.205, extractAmount(total, 2));
        assertEquals(64.005, extractAmount(total, 3));
        assertEquals(64.005, extractAmount(total, 4));
    }

    @Test
    void totalSpentAmountByMonthForDriver2Test() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonthWithDriver(2010, 2);
        assertEquals(2, total.size());
        assertEquals(50.2, extractAmount(total, 1));
        assertEquals(70.46, extractAmount(total, 2));
    }

    @Test
    void totalSpentAmountByMonthForDriver3Test() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonthWithDriver(2010, 3);
        assertEquals(2, total.size());
        assertEquals(121.95, extractAmount(total, 1));
        assertEquals(66.515, extractAmount(total, 2));
    }

    @Test
    void totalSpentAmountByMonthForDriver4Test() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonthWithDriver(2010, 4);
        assertEquals(3, total.size());
        assertEquals(130.95, extractAmount(total, 1));
        assertEquals(62.37, extractAmount(total, 2));
        assertEquals(207.315, extractAmount(total, 3));
    }

    @Test
    void totalSpentAmountByMonthForDriver5Test() {
        List<TotalSpentAmountByMonth[]> total = repo.totalSpentAmountByMonthWithDriver(2010, 5);
        assertEquals(1, total.size());
        assertEquals(139.95, extractAmount(total, 1));
    }


    //Helpers

    private Double extractAmount(List<TotalSpentAmountByMonth[]> total, final Integer month) {
        for (TotalSpentAmountByMonth[] totalSpentAmountByMonth : total) {
            for (TotalSpentAmountByMonth spentAmountByMonth : totalSpentAmountByMonth) {
                if (month.equals(spentAmountByMonth.getMonth())) {
                    return spentAmountByMonth.getAmount();
                }
            }
        }
        return null;
    }
}