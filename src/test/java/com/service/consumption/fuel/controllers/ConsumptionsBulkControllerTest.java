package com.service.consumption.fuel.controllers;

import com.service.consumption.fuel.service.ConsumptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class ConsumptionsBulkControllerTest {

    private final String endpoint = "/consumptions/bulk";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumptionService service;

    @Test
    public void bulkCreateConsumptionsTest() throws Exception {
        String str = "[\n" +
                "  {\n" +
                "    \"driverId\": 1,\n" +
                "    \"date\": \"12.11.2010\",\n" +
                "    \"type\": \"D\",\n" +
                "    \"liters\": 90,\n" +
                "    \"price\": 1.101\n" +
                "  },\n" +
                "  {\n" +
                "    \"driverId\": 2,\n" +
                "    \"date\": \"13.11.2010\",\n" +
                "    \"type\": \"D\",\n" +
                "    \"liters\": 90,\n" +
                "    \"price\": -1.201\n" +
                "  },\n" +
                "  {\n" +
                "    \"driverId\": 3,\n" +
                "    \"date\": \"14.11.2010\",\n" +
                "    \"type\": \"E98\",\n" +
                "    \"liters\": 50,\n" +
                "    \"price\": 1.222\n" +
                "  }\n" +
                "]";

        MockMultipartFile firstFile = new MockMultipartFile("file", "consumptions.json", "text/plain", str.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart(endpoint)
                .file(firstFile))
                .andExpect(status().isOk());
        verify(service, times(1)).saveConsumptions(any(List.class));
    }

    @Test
    public void bulkCreateConsumptionsBadTest() throws Exception {
        String str = "[\n" +
                "  {\n" +
                "    \"driverId\": 1,\n" +
                "    \"date\": \"12.11.2010\",\n" +
                "    \"type\": \"D\",\n" +
                "    \"liters\": 90,\n" +
                "    \"price\": 1.101\n" +
                "  },\n" +
                "  {\n" +
                "    \"driverId\": 2,\n" +
                "    \"date\": \"13.11.2010\",\n" +
                "    \"type\": \"-D\",\n" +
                "    \"liters\": 90,\n" +
                "    \"price\": -1.201\n" +
                "  },\n" +
                "  {\n" +
                "    \"driverId\": 3,\n" +
                "    \"date\": \"14.11.2010\",\n" +
                "    \"type\": \"E98\",\n" +
                "    \"liters\": 50,\n" +
                "    \"price\": 1.222\n" +
                "  }\n" +
                "]";


        MockMultipartFile firstFile = new MockMultipartFile("file", "consumptions.json", "text/plain", str.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart(endpoint)
                .file(firstFile))
                .andExpect(status().isBadRequest());
        verify(service, times(0)).saveConsumptions(any(List.class));
    }

}