package com.in28minutes.microservices.currency_exchange_services;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.in28minutes.microservices.currency_exchange_services.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_services.CurrencyExchangeController;
import com.in28minutes.microservices.currency_exchange_services.CurrencyExchangeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerWebTest {
    @MockitoBean
    private CurrencyExchangeRepository repository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void shouldReturnExchangeRateViaHttp() throws Exception {
        CurrencyExchange exchange = new CurrencyExchange();

        exchange.setId(10001L);
        exchange.setFrom("USD");
        exchange.setTo("INR");
        exchange.setConversionMultiple(BigDecimal.valueOf(50));
        when(repository.findByFromcurrencyAndTocurrency("USD", "INR"))
                .thenReturn(exchange);
        mockMvc.perform(get("/currency-exchange/from/USD/to/INR"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.from").value("USD"))
                .andExpect(jsonPath("$.to").value("INR"))
                .andExpect(jsonPath("$.conversionMultiple").value(50));

    }
    @Test
    void shouldReturnNotFoundWhenExchangeRateDoesNotExist() throws Exception {
        when(repository.findByFromcurrencyAndTocurrency("USD", "XYZ"))
                .thenReturn(null);
        mockMvc.perform(get("/currency-exchange/from/USD/to/XYZ"))
                .andExpect(status().isNotFound());

    }

}

