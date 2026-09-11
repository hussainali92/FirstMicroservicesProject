package com.in28minutes.microservices.currency_conversion_services;

import com.in28minutes.microservices.currency_conversion_services.Proxy.CurrencyExchangeProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionControllerTest {
    @Mock
    private CurrencyExchangeProxy proxy;

    @InjectMocks
    private CurrencyConversionController controller;
    @Test
    void shouldCalculateTotalAmountUsingExchangeRate() {

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
        CurrencyConversion result =
                controller.currencyConversionFeign(
                        "USD",
                        "INR",
                        BigDecimal.TEN
                );
        assertEquals("USD", result.getFromcurrency());
        assertEquals("INR", result.getTocurrency());
        assertEquals(BigDecimal.TEN, result.getQuantity());
        assertEquals(BigDecimal.valueOf(50), result.getConversionMultiple());
        assertEquals(BigDecimal.valueOf(500), result.getTotalAmount());
        assertEquals("8000", result.getEnvironment());
        verify(proxy).GetParamsFromCurrencyExchange("USD", "INR");
    }
    @Test
    void shouldReturnFallbackResponse() {
        CurrencyConversion result =
                controller.currencyConversionFeignFallback(
                        "USD",
                        "INR",
                        BigDecimal.TEN,
                        new RuntimeException("Currency Exchange is down")
                );
        assertEquals("USD", result.getFromcurrency());
        assertEquals("INR", result.getTocurrency());
        assertEquals(BigDecimal.TEN, result.getQuantity());
        assertEquals(BigDecimal.ONE, result.getConversionMultiple());
        assertEquals(BigDecimal.TEN, result.getTotalAmount());
        assertEquals("fallback-Response", result.getEnvironment());


    }
}
