package com.in28minutes.microservices.currency_exchange_services;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository <CurrencyExchange , Long>{

    CurrencyExchange findByFromcurrencyAndTocurrency(String from ,String  to );
}
