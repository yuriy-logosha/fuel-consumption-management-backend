package com.service.consumption.fuel.controllers;

import com.service.consumption.fuel.projections.ConsumptionsPerMonth;
import com.service.consumption.fuel.projections.StatisticsByMonth;
import com.service.consumption.fuel.projections.TotalSpentAmountByMonth;
import com.service.consumption.fuel.repository.ConsumptionRepository;
import com.service.consumption.fuel.responses.MessageOnlyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ConsumptionRepository repo;

    @GetMapping(value = "/totalSpentAmountByMonth")
    public List<TotalSpentAmountByMonth[]> totalSpentAmountByMonth(@Min(1970) @RequestParam int year,
                                                                   @Min(0) @RequestParam(required = false) Integer driver) {
        return (driver != null) ? repo.totalSpentAmountByMonthWithDriver(year, driver) : repo.totalSpentAmountByMonth(year);
    }

    @GetMapping(value = "/consumptionsByMonth")
    public List<ConsumptionsPerMonth[]> consumptionsByMonth(@Min(1) @Max(12) @RequestParam int month,
                                                            @Min(1970) @RequestParam int year,
                                                            @Min(0) @RequestParam(required = false) Integer driver) {
        return (driver != null) ? repo.consumptionsPerMonthWithDriver(month, year, driver) : repo.consumptionsPerMonth(month, year);
    }

    @GetMapping(value = "/statisticsForMonth")
    public List<StatisticsByMonth[]> statisticsForMonth(@Min(1970) @RequestParam int year,
                                                        @Min(0) @RequestParam(required = false) Integer driver) {
        return (driver != null) ? repo.statisticsForMonthWithDriver(year, driver) : repo.statisticsForMonth(year);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MessageOnlyResponse handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.debug("Report controller exception: ", e);
        return new MessageOnlyResponse(e.getMessage());
    }

}
