package com.service.consumption.fuel.projections;

public interface StatisticsByMonth {

    String getType();

    Integer getSum_liters();

    Double getAverage_price();

    Integer getMonth();

}


