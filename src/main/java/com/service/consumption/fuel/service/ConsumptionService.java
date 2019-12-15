package com.service.consumption.fuel.service;

import com.service.consumption.fuel.model.Consumption;
import com.service.consumption.fuel.repository.ConsumptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ConsumptionService {

    @Resource
    private ConsumptionRepository repo;

    @Transactional
    public Iterable<Consumption> saveConsumptions(@Valid List<Consumption> collection){
        return repo.saveAll(collection);
    }
}
