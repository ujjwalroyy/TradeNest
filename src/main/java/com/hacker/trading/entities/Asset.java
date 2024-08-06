package com.hacker.trading.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double quantity;

    private double buyPrice;

    @ManyToMany
    private Coin coin;

    @ManyToOne
    private User user;
}
