package com.arcosoft.stock.dbservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private Integer id;

    @Column(name = "quote")
    private String quote;

    @Column(name = "user_name")
    private String userName;

    public Quote() {
    }

    public Quote(String quote, String userName) {
        this.quote = quote;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
