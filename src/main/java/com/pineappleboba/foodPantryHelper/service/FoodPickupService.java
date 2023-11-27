package com.pineappleboba.foodPantryHelper.service;

import com.pineappleboba.foodPantryHelper.model.FoodOrder;
import com.pineappleboba.foodPantryHelper.model.FoodOrderItem;
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
        chosenFoodItemNames = addPantryStapleBag(chosenFoodItemNames, foodOrder.getPantryStapleBagType());
        FoodPickup foodPickup = new FoodPickup(
                0,
                foodOrder.getPersonIdentifier(),
                foodOrder.getOrdinalInGroup(),
                foodOrder.getGroupSize(),
                foodOrder.getFamilySize(),
                chosenFoodItemNames
                );
        foodPickupRepository.save(foodPickup);
    }

    private String addPantryStapleBag(String chosenFoodItemNamesCsv, String pantryStapleBagType) {
        if (null == pantryStapleBagType || pantryStapleBagType.isEmpty()) {
            return chosenFoodItemNamesCsv;
        }
        if (chosenFoodItemNamesCsv.isEmpty()) {
            return pantryStapleBagType + " bag";
        }

        return pantryStapleBagType + " bag," + chosenFoodItemNamesCsv;
    }

    private String getNameCsvFromFoodItems(List<FoodOrderItem> chosenFoodItems) {
        //return chosenFoodItems.stream().map(FoodItem::getName).reduce((a,b) -> a + "," + b).orElse("");
        return chosenFoodItems.stream()
                .map((item -> "(%d) %s".formatted(item.getQuantityOrdered(),item.getItem().getName())))
                .reduce((a,b) -> a + "," + b).orElse("");
    }
}
