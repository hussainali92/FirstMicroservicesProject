package com.in28minutes.microservices.currency_conversion_services;

import java.math.BigDecimal;
import java.math.MathContext;

public class CurrencyConversion {

    private int id ;
    private String fromcurrency ;
    private String tocurrency;
    private BigDecimal quantity ;
    private BigDecimal conversionMultiple;
    private BigDecimal totalAmount;
    private String environment;



    public CurrencyConversion(int id, String fromcurrency, String tocurrency, BigDecimal quantity, BigDecimal conversionMultiple, BigDecimal totalAmount, String environment) {
        this.id = id;
        this.fromcurrency = fromcurrency;
        this.tocurrency = tocurrency;
        this.quantity = quantity;
        this.conversionMultiple = conversionMultiple;
        this.totalAmount = totalAmount;
        this.environment = environment;
    }
    public CurrencyConversion() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromcurrency() {
        return fromcurrency;
    }

    public void setFromcurrency(String fromcurrency) {
        this.fromcurrency = fromcurrency;
    }

    public String getTocurrency() {
        return tocurrency;
    }

    public void setTocurrency(String tocurrency) {
        this.tocurrency = tocurrency;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
