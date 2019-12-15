package com.service.consumption.fuel.repository;

import com.service.consumption.fuel.model.Consumption;
import com.service.consumption.fuel.projections.ConsumptionsPerMonth;
import com.service.consumption.fuel.projections.StatisticsByMonth;
import com.service.consumption.fuel.projections.TotalSpentAmountByMonth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ConsumptionRepository extends CrudRepository<Consumption, Long> {

    @Query(value = "SELECT SUM(liters*price) AS amount, extract(month from date) as month FROM Consumption WHERE extract(year from date) = :year GROUP BY extract(month from date)")
    List<TotalSpentAmountByMonth[]> totalSpentAmountByMonth(@Param("year") Integer year);

    @Query(value = "SELECT SUM(liters*price) AS amount, extract(month from date) as month FROM Consumption WHERE extract(year from date) = :year and driverId = :driver GROUP BY extract(month from date)")
    List<TotalSpentAmountByMonth[]> totalSpentAmountByMonthWithDriver(@Param("year") Integer year, @Param("driver") Integer driver);

    @Query(value = "SELECT type as type, liters as liters, date as date, price as price, liters*price as amount, driverId as driver_id FROM Consumption WHERE extract(year from date) = :y and extract(month from date) = :m")
    List<ConsumptionsPerMonth[]> consumptionsPerMonth(@Param("m") Integer month, @Param("y") Integer year);

    @Query(value = "SELECT type as type, liters as liters, date as date, price as price, liters*price as amount, driverId as driver_id FROM Consumption WHERE extract(year from date) = :y and extract(month from date) = :m and driverId = :driver")
    List<ConsumptionsPerMonth[]> consumptionsPerMonthWithDriver(@Param("m") Integer month, @Param("y") Integer year, @Param("driver") Integer driver);

    @Query(value = "SELECT type as type, sum(liters) as sum_liters, avg(liters*price) as average_price, extract(month from date) as month FROM Consumption where extract(year from date) = :y GROUP BY type, extract(month from date)")
    List<StatisticsByMonth[]> statisticsForMonth(@Param("y") Integer year);

    @Query(value = "SELECT type as type, sum(liters) as sum_liters, avg(liters*price) as average_price, extract(month from date) as month FROM Consumption where extract(year from date) = :y and driverId = :driver GROUP BY type, extract(month from date)")
    List<StatisticsByMonth[]> statisticsForMonthWithDriver(@Param("y") Integer year, @Param("driver") Integer driver);

}
