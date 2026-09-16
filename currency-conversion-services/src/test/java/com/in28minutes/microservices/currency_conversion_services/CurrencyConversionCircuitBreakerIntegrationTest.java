package com.in28minutes.microservices.currency_conversion_services;
import com.in28minutes.microservices.currency_conversion_services.Proxy.CurrencyExchangeProxy;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyConversionCircuitBreakerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CurrencyExchangeProxy proxy;
    @Test
    void shouldReturnFallbackWhenCurrencyExchangeFails() throws Exception {

        // ARRANGE
        when(proxy.GetParamsFromCurrencyExchange("USD", "INR"))
                .thenThrow(new RuntimeException("Currency Exchange is down"));

        // ACT + ASSERT
        mockMvc.perform(
                        get("/currency-conversion-feign/from/USD/to/INR/quantity/10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.environment").value("fallback-Response"));
    }

}