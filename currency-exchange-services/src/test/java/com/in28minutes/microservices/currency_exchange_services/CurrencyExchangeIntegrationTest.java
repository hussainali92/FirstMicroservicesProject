package com.in28minutes.microservices.currency_exchange_services;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyExchangeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyExchangeRepository repository;
    @Test
    void shouldReturnExchangeRateFromDatabase() throws Exception {
        CurrencyExchange exchange = new CurrencyExchange();

        exchange.setId(10001L);
        exchange.setFrom("USD");
        exchange.setTo("INR");
        exchange.setConversionMultiple(BigDecimal.valueOf(50));

        repository.save(exchange);
        mockMvc.perform(
                        get("/currency-exchange/from/USD/to/INR")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value("USD"))
                .andExpect(jsonPath("$.to").value("INR"))
                .andExpect(jsonPath("$.conversionMultiple").value(50));;


    }


}