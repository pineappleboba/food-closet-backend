package com.pineappleboba.foodPantryHelper.model;

import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import lombok.Value;

@Value
public class FoodOrderItem {
    FoodItem item;
    int quantityOrdered;
}
