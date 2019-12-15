package com.service.consumption.fuel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table
public class Consumption {

    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;

    @Positive
    @NotNull(message = "Driver is mandatory")
    private Integer driverId;

    @JsonFormat(pattern="dd.MM.yyyy")
    @NotNull(message = "Date is mandatory")
    private Date date;

    @Positive
    @NotNull(message = "Volume in liters is mandatory")
    private Integer liters;

    @NotNull(message = "Type is mandatory")
    @Enumerated(EnumType.STRING)
    private TYPES type;

    @Positive
    @NotNull(message = "Price is mandatory")
    private Double price;

    public Consumption(int driver, Date d, Integer l, String t, Double p) {
        this.driverId = driver;
        this.date = d;
        this.liters = l;
        this.type = TYPES.valueOf(t);
        this.price = p;
    }
}


