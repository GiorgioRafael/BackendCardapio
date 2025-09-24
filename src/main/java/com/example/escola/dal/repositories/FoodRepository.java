package com.example.escola.dal.repositories;

import com.example.escola.dal.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
