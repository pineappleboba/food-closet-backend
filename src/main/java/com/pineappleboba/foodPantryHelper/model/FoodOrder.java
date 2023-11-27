package com.pineappleboba.foodPantryHelper.model;

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
    List<FoodOrderItem> chosenFoodItems;
}
