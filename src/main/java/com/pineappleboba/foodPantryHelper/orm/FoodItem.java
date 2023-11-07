package com.pineappleboba.foodPantryHelper.orm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {
    @Id
    @GeneratedValue
    int id;
    int foodChoiceId;
    String name;
    int largeFamilyQuantity;
    int smallFamilyQuantity;
    int quantityAvailable;
}
