package com.in28minutes.microservices.currency_exchange_services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeControllerTest {
    @Mock
    private CurrencyExchangeRepository repository;
    @Mock
    private Environment environment;
    @InjectMocks
    private CurrencyExchangeController controller;

    @Test
    void shouldReturnExchangeRate() {

        CurrencyExchange exchange = new CurrencyExchange();
        exchange.setId(10001L);
        exchange.setFrom("USD");
        exchange.setTo("INR");
        exchange.setConversionMultiple(BigDecimal.valueOf(50));
        when(repository.findByFromcurrencyAndTocurrency("USD", "INR"))
                .thenReturn(exchange);
        when(environment.getProperty("local.server.port"))
                .thenReturn("8000");
        // Act
        CurrencyExchange result = controller.currencyEchange("USD", "INR");
        // Assert
        assertEquals("USD", result.getFrom());
        assertEquals("INR", result.getTo());
        assertEquals(BigDecimal.valueOf(50), result.getConversionMultiple());
        assertEquals(10001L, result.getId());
        verify(repository).findByFromcurrencyAndTocurrency("USD", "INR");

    }
    @Test
    void shouldHandleExchangeRateNotFound() {

        // Arrange
        when(repository.findByFromcurrencyAndTocurrency("USD", "XYZ"))
                .thenReturn(null);

        // Act + Assert
        assertThrows(NotFound.class, () -> {
            controller.currencyEchange("USD", "XYZ");
        });
    }
}
