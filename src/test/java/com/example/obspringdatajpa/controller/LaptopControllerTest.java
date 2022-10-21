package com.example.obspringdatajpa.controller;

import com.example.obspringdatajpa.entities.LaptopEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

   @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @Test
    void saludo() {
     ResponseEntity<String> response =
             testRestTemplate.getForEntity("/hola", String.class);

     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(200,response.getStatusCodeValue());
     assertEquals("Hola mundo", response.getBody());
   }

    @Test
    void findAll() {
     ResponseEntity<LaptopEntity[]> response =
             testRestTemplate.getForEntity("/api/laptops1", LaptopEntity[].class);
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(200,response.getStatusCodeValue());

     List<LaptopEntity> laptop = Arrays.asList(response.getBody());
     System.out.println(laptop.size());

    }
/*
    @Test
    void findOneById() {
     ResponseEntity<String> response =
             testRestTemplate.getForEntity("/api/laptop2/1", String.class);

     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

 */

    @Test
    void create() {
     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);
     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

     String json = """
             {
             
                
                 "marca":"PHP",
                 "modelo":"bf4517",
                 "precio":200.58,
                 "date":"2020-01-15"
                 
             
             }
             """;
     HttpEntity<String> request = new HttpEntity<>(json,headers);

     ResponseEntity<LaptopEntity> response = testRestTemplate.exchange("/api/laptops3",HttpMethod.POST,request,LaptopEntity.class);

     LaptopEntity result = response.getBody();

     assertEquals(1L,result.getId());
     assertEquals("PHP",result.getMarca());
     assertEquals(200.58,result.getPrecio());

    }
}