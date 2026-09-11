package com.in28minutes.microservices.currency_conversion_services;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.in28minutes.microservices.currency_conversion_services.Proxy.CurrencyExchangeProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyConversionController.class)
class CurrencyConversionControllerWebTest {

    @MockitoBean
    private CurrencyExchangeProxy proxy;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void shouldReturnConvertedAmountViaHttp() throws Exception {

        // ARRANGE
        CurrencyConversion exchangeResponse = new CurrencyConversion(
                10001,
                "USD",
                "INR",
                BigDecimal.ONE,
                BigDecimal.valueOf(50),
                BigDecimal.ONE,
                "8000"
        );

        when(proxy.GetParamsFromCurrencyExchange("USD", "INR"))
                .thenReturn(exchangeResponse);
        mockMvc.perform(
                        get("/currency-conversion-feign/from/USD/to/INR/quantity/10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fromcurrency").value("USD"))
                .andExpect(jsonPath("$.tocurrency").value("INR"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.conversionMultiple").value(50))
                .andExpect(jsonPath("$.totalAmount").value(500))
                .andExpect(jsonPath("$.environment").value("8000"));;

    }
    @Test
    void shouldReturnFallbackWhenCurrencyExchangeFails() throws Exception {
        when(proxy.GetParamsFromCurrencyExchange("USD", "INR"))
                .thenThrow(new RuntimeException("Currency Exchange is down"));
        mockMvc.perform(
                        get("/currency-conversion-feign/from/USD/to/INR/quantity/10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.environment").value("fallback-Response"));

    }
}

