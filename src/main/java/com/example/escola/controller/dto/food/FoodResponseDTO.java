package com.example.escola.controller.dto.food;

import com.example.escola.dal.entities.Food;

public record FoodResponseDTO(long id, String title, String image, Integer price) {

    public FoodResponseDTO(Food food){
        this(food.getId(), food.getTitle(),food.getImage(), food.getPrice());
    }

}
