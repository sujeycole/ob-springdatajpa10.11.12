package com.example.obspringdatajpa.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Laptops")
@ApiModel("Entidad laptop para representar un elemento ")
public class LaptopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave ficticia autoincremental tipo Long")
    private Long id;

    private String marca;

    private String modelo;

    @ApiModelProperty("Precio en rupias, con dos decimales utiliando . como separador")
    private Double precio;

    private LocalDate date;

    public LaptopEntity() {
    }

    public LaptopEntity(Long id, String marca, String modelo, Double precio, LocalDate date) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LaptopEntity{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", date=" + date +
                '}';
    }
}
