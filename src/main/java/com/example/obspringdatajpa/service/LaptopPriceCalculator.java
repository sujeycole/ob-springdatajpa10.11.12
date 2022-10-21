package com.example.obspringdatajpa.service;

import com.example.obspringdatajpa.entities.LaptopEntity;

public class LaptopPriceCalculator {

    public double calculatePrice(LaptopEntity laptopEntity){
        double price = laptopEntity.getPrecio();

        if(laptopEntity.getMarca().equalsIgnoreCase("PHP")){
            price += 200.80;
        }
        // envio
        price += 20.99;
        return price;
    }
}
