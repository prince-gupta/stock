package com.arcosoft.stock.dbservice.repositories;

import com.arcosoft.stock.dbservice.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by princegupta on 20/12/17.
 */
public interface QuoteRepository extends JpaRepository<Quote, Integer>{
    List<Quote> findByUserName(String username);
}
