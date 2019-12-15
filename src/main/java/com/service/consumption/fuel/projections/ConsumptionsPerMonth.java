package com.service.consumption.fuel.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.consumption.fuel.model.TYPES;

import java.util.Date;

public interface ConsumptionsPerMonth {

    TYPES getType();

    @JsonFormat(pattern="dd.MM.yyyy")
    Date getDate();

    Double getPrice();

    Double getAmount();

    Integer getDriver_id();

}


