package com.example.obspringdatajpa.service;

import com.example.obspringdatajpa.entities.LaptopEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LaptopPriceCalculatorTest {

    @Test
    void calculatePrice() {
        LaptopEntity laptop = new LaptopEntity(1L,"PHP",  "bs3426", 200.50,LocalDate.now());
        //configuracion de la prueba
        LaptopPriceCalculator calculator = new LaptopPriceCalculator();
        calculator.calculatePrice(laptop);

        //se ejecuta el comportamiento de testear
        double price = calculator.calculatePrice(laptop);
        System.out.println(price);

        //comprovacions aserciones
        assertTrue(price > 0);
        assertEquals(422.29,price);
    }
}