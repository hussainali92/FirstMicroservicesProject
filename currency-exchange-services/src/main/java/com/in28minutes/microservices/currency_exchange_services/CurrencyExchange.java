package com.in28minutes.microservices.currency_exchange_services;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.math.BigDecimal;
@Entity
public class CurrencyExchange {
    @Id
    private Long id;
    private String fromcurrency;
    private String tocurrency ;
    private BigDecimal conversionMultiple ;
    private String environment;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.fromcurrency = from;
        this.tocurrency = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return fromcurrency;
    }

    public void setFrom(String from) {
        this.fromcurrency = from;
    }

    public String getTo() {
        return tocurrency;
    }

    public void setTo(String to) {
        this.tocurrency = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;

    }
}
