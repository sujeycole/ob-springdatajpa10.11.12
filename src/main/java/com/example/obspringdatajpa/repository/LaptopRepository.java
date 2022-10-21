package com.example.obspringdatajpa.repository;

import com.example.obspringdatajpa.entities.LaptopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {

}
