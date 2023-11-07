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
public class FoodPickup {
    @Id
    @GeneratedValue
    private int id;
    private String personIdentifier;
    private int ordinalInGroup;
    private int groupSize;
    private String familySize;
    private String pantryBag;
    private String foodItemNames;
}
