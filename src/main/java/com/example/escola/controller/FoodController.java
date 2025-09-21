package com.example.escola.controller;

import com.example.escola.food.Food;
import com.example.escola.food.FoodRepository;
import com.example.escola.food.FoodRequestDTO;
import com.example.escola.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController //anotacao para mapear que é um controller. precisa settar o endpoit
@RequestMapping("foods") //mapeando o endpoint e o determinando como "food"

public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders="*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
        return;
    }

    @GetMapping //quando baterem no endpoint food, vai utilizar o metodo abaixo \/
    //public List<Food> getAll() {
    public List<FoodResponseDTO> getAll() {

        List<FoodResponseDTO> foodList = repository.findAll().stream()
                .map(FoodResponseDTO::new)
                .collect(Collectors.toList());
        return foodList; //nao é uma boa prática, deve-se criar DTO (Data transfer object)
    }

}
