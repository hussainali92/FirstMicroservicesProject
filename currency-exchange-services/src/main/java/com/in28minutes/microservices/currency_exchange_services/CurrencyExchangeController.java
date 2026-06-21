package com.in28minutes.microservices.currency_exchange_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    @Autowired
    public Environment environment;
    @Autowired
    public CurrencyExchangeRepository jpaRepository ;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange currencyEchange(@PathVariable String from ,@PathVariable String to){
          //CurrencyExchange currencyExchange = new CurrencyExchange(1000L , from ,to , BigDecimal.valueOf(50));
          CurrencyExchange currencyExchange = jpaRepository.findByFromcurrencyAndTocurrency(from, to);
          if(currencyExchange == null) throw new  NotFound("Currency Exchange Not Found");
          String port = environment.getProperty("local.server.port");
          currencyExchange.setEnvironment(port);
          return currencyExchange;
    }
}
