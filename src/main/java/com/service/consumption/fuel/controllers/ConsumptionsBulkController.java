package com.service.consumption.fuel.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.consumption.fuel.model.Consumption;
import com.service.consumption.fuel.service.ConsumptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@Validated
public class ConsumptionsBulkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumptionsBulkController.class);

    @Autowired
    private ConsumptionService service;

    @PostMapping(value = "/consumptions/bulk", produces = "application/json")
    public ResponseEntity<Iterable<Consumption>> bulkCreateConsumptions(@RequestParam("file") @Valid @NotNull MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Consumption> consumptions = mapper.readValue(file.getInputStream(), new TypeReference<List<Consumption>>() {});
            return ResponseEntity.ok(service.saveConsumptions(consumptions));
        } catch (IOException | ConstraintViolationException e) {
            LOGGER.debug("Bulk operation exception: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
