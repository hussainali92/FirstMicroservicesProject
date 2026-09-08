package com.in28minutes.microservices.currency_exchange_services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CurrencyExchangeRepositoryTest {
    @Autowired
    private CurrencyExchangeRepository repository;

    @Test
    void shouldFindExchangeRateByFromAndTo() {
        CurrencyExchange exchange = new CurrencyExchange();

        exchange.setId(10001L);
        exchange.setFrom("USD");
        exchange.setTo("INR");
        exchange.setConversionMultiple(BigDecimal.valueOf(50));
        repository.save(exchange);
        CurrencyExchange result =
                repository.findByFromcurrencyAndTocurrency("USD", "INR");
        assertNotNull(result);
        assertEquals("USD", result.getFrom());
        assertEquals("INR", result.getTo());
        assertEquals(0, BigDecimal.valueOf(50).compareTo(result.getConversionMultiple()));

    }
}
