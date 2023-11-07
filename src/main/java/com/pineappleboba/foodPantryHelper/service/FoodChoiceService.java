package com.pineappleboba.foodPantryHelper.service;

import com.pineappleboba.foodPantryHelper.model.FoodOrder;
import com.pineappleboba.foodPantryHelper.orm.FoodChoice;
import com.pineappleboba.foodPantryHelper.orm.FoodChoiceRepository;
import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import com.pineappleboba.foodPantryHelper.orm.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FoodChoiceService {
    protected static final int LARGE_FAMILY_MINIMUM = 5;
    private final FoodChoiceRepository foodChoiceRepository;
    private final FoodItemRepository foodItemRepository;

    public FoodChoiceService(FoodChoiceRepository foodChoiceRepository, FoodItemRepository foodItemRepository) {
        this.foodChoiceRepository = foodChoiceRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public List<FoodChoice> getFoodChoices(int numberInFamily) {
        if (numberInFamily >= LARGE_FAMILY_MINIMUM) {
            Predicate<FoodItem> predicate = item -> item.getLargeFamilyQuantity() > 0 && item.getQuantityAvailable() > 0;
            return getAndFilterFoodChoices(predicate);
        } else {
            Predicate<FoodItem> predicate = item -> item.getSmallFamilyQuantity() > 0 && item.getQuantityAvailable() > 0;
            return getAndFilterFoodChoices(predicate);
        }
    }

    private List<FoodChoice> getAndFilterFoodChoices(Predicate<FoodItem> predicate) {
        List<FoodChoice> foodChoices = foodChoiceRepository.findAll();
        foodChoices.forEach(choice -> choice.setFoodItemsInChoice(
                choice.getFoodItemsInChoice().stream().filter(predicate).collect(Collectors.toList())
        ));
        return foodChoices.stream().filter(choice -> !choice.getFoodItemsInChoice().isEmpty()).collect(Collectors.toList());
    }

    public void updateQuantities(FoodOrder foodOrder) {
        // todo: database locking between retrieving the foodItem and updating its quantity
        for(Iterator<FoodItem> foodItemIterator = foodOrder.getChosenFoodItems().iterator(); foodItemIterator.hasNext();) {
            FoodItem foodItem = foodItemIterator.next();
            Optional<FoodItem> savedFoodItem = foodItemRepository.findById(foodItem.getId());
            if (savedFoodItem.isEmpty()) {
                // log an error
            } else {
                FoodItem foodItemWithUpdatedQuantity = new FoodItem(
                        savedFoodItem.get().getId(),
                        savedFoodItem.get().getFoodChoiceId(),
                        savedFoodItem.get().getName(),
                        savedFoodItem.get().getLargeFamilyQuantity(),
                        savedFoodItem.get().getSmallFamilyQuantity(),
                        savedFoodItem.get().getQuantityAvailable()-1
                );
                foodItemRepository.save(foodItemWithUpdatedQuantity);
            }
        }
    }
}