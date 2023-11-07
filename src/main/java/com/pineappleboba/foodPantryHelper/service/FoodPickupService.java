package com.pineappleboba.foodPantryHelper.service;

import com.pineappleboba.foodPantryHelper.model.FoodOrder;
import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import com.pineappleboba.foodPantryHelper.orm.FoodPickup;
import com.pineappleboba.foodPantryHelper.orm.FoodPickupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodPickupService {
    private final FoodPickupRepository foodPickupRepository;

    public FoodPickupService(FoodPickupRepository foodPickupRepository) {
        this.foodPickupRepository = foodPickupRepository;
     }

    public List<FoodPickup> getAllFoodPickups() {
        return foodPickupRepository.findAll();
    }

    public void createPickup(FoodOrder foodOrder) {
        String chosenFoodItemNames = getNameCsvFromFoodItems(foodOrder.getChosenFoodItems());
        FoodPickup foodPickup = new FoodPickup(
                0,
                foodOrder.getPersonIdentifier(),
                foodOrder.getOrdinalInGroup(),
                foodOrder.getGroupSize(),
                foodOrder.getFamilySize(),
                foodOrder.getPantryStapleBagType(),
                chosenFoodItemNames
                );
        foodPickupRepository.save(foodPickup);
    }

    private String getNameCsvFromFoodItems(List<FoodItem> chosenFoodItems) {
        return chosenFoodItems.stream().map(FoodItem::getName).reduce((a,b) -> a + "," + b).orElse("");
    }
}
