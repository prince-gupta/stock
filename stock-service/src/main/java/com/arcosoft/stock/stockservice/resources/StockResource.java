package com.arcosoft.stock.stockservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallback", groupKey = "quotes",
            commandKey = "getQuote",
            threadPoolKey = "quote-key"
    )

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") String username) {
        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/quote/" + username, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<String>>() {
        });
        return quoteResponse.getBody().stream()
                .map(quote -> {
                    Stock stock = getYahooStock(quote);
                    return new Quote(quote, stock.getQuote().getPrice());
                }).collect(Collectors.toList());
    }


    public List<Quote> fallback(String userName) {
        return new ArrayList<>();
    }

    private Stock getYahooStock(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }

    private class Quote {
        private final String quote;
        private final BigDecimal price;

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public String getQuote() {
            return quote;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}
