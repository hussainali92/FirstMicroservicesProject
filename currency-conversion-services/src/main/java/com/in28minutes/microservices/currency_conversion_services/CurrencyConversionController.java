package com.in28minutes.microservices.currency_conversion_services;

import com.in28minutes.microservices.currency_conversion_services.Proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
@RestController
public class CurrencyConversionController {
    @Autowired
    public CurrencyExchangeProxy proxy ;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion currencyConversion(@PathVariable String from , @PathVariable String to , @PathVariable BigDecimal quantity){
        CurrencyConversion currencyConversion = new CurrencyConversion(10001 , from , to , BigDecimal.ONE, BigDecimal.ONE ,BigDecimal.ONE , null);
         return currencyConversion;
    }
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion currencyConversionFeign(@PathVariable String from , @PathVariable String to , @PathVariable BigDecimal quantity){
        CurrencyConversion currencyConversion = proxy.GetParamsFromCurrencyExchange(from , to);

        return new CurrencyConversion(currencyConversion.getId(), from , to ,quantity , currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment());
    }
}
