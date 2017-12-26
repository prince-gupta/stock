package com.arcosoft.stock.dbservice.resources;

import com.arcosoft.stock.dbservice.entity.Quote;
import com.arcosoft.stock.dbservice.model.Quotes;
import com.arcosoft.stock.dbservice.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by princegupta on 20/12/17.
 */
@RestController
@RequestMapping("/quote")
public class DBResource {

    @Autowired
    QuoteRepository quoteRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username) {

        return findQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> addQuote(@RequestBody final Quotes quotes) {
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quote, quotes.getUserName()))
                .forEach(quote -> quoteRepository.save(quote));
        return findQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> deleteQuote(@PathVariable("username") String username){
        quoteRepository.delete(quoteRepository.findByUserName(username));
        return findQuotesByUserName(username);
    }

    private List<String> findQuotesByUserName(@PathVariable("username") String username) {
        return quoteRepository.findByUserName(username)
                .stream()
                .map(quote -> quote.getQuote())
                .collect(Collectors.toList());

    }
}
