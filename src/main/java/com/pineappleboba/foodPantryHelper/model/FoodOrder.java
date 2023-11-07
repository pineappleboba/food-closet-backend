package com.pineappleboba.foodPantryHelper.model;

import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import lombok.Value;

import java.util.List;

@Value
public class FoodOrder {
    //Integer appointmentId;
    //String appointmentNotes;
    String personIdentifier;
    int ordinalInGroup;
    int groupSize;
    String familySize;
    String pantryStapleBagType;
    List<FoodItem> chosenFoodItems;
}
