package com.pineappleboba.foodPantryHelper.orm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodChoice {
    @Id
    @GeneratedValue
    int Id;

    @OneToMany(targetEntity= FoodItem.class)
    @JoinColumn(name="foodChoiceId", referencedColumnName = "id")
    List<FoodItem> foodItemsInChoice;
}
