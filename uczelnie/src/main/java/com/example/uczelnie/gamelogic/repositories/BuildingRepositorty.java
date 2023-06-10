package com.example.uczelnie.gamelogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.uczelnie.gamelogic.Building;


@Repository
public interface BuildingRepositorty extends JpaRepository<Building, Long> {
    
}