package com.example.obspringdatajpa.controller;

import com.example.obspringdatajpa.entities.LaptopEntity;
import com.example.obspringdatajpa.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    @Autowired
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //buscar todas las laptop
    @GetMapping("/api/laptops1")
    public List<LaptopEntity> findAll() {
        return laptopRepository.findAll();
    }

    //buscar una laptop por el ID
    @GetMapping("/api/laptop2/{id}")
    public ResponseEntity<LaptopEntity> findOneById(@PathVariable Long id) {
        Optional<LaptopEntity> laptop = laptopRepository.findById(id);

        if (laptop.isPresent())
            return ResponseEntity.ok(laptop.get());
        else
            return ResponseEntity.notFound().build();


    }

    //crear una laptop en base de datos
    @PostMapping("/api/laptops3")
    public ResponseEntity<Object> create(@RequestBody LaptopEntity laptopEntity, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        if (laptopEntity.getId() != null) {//si es verdadera quiere decir que ya hay un id creado y no es una creacion.
            log.warn("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        LaptopEntity resultado = laptopRepository.save(laptopEntity);
        return ResponseEntity.ok(resultado);
    }

    // actualizar una laptop existente en base de datos
    @PutMapping("/api/laptops4")
    public ResponseEntity<LaptopEntity> update(@RequestBody LaptopEntity laptopEntity) {
        if (laptopEntity.getId() == null) {// si no tiene id quiere decir que sí es una creación
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptopEntity.getId())) {
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        LaptopEntity result = laptopRepository.save(laptopEntity);
        return ResponseEntity.ok(result);
    }

    //borrar una laptop en base de datos por el ID
    @ApiIgnore
    @DeleteMapping("/api/laptops5/{id}")
    public ResponseEntity<LaptopEntity> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore
    @DeleteMapping("/api/laptops6")
    public ResponseEntity<LaptopEntity> deleteAll(){
        log.info("REST Request for delete all laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}


