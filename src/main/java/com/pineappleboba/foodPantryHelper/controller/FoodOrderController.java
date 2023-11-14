package com.pineappleboba.foodPantryHelper.controller;

import com.pineappleboba.foodPantryHelper.model.FoodOrder;
import com.pineappleboba.foodPantryHelper.orm.FoodChoice;
import com.pineappleboba.foodPantryHelper.orm.FoodPickup;
import com.pineappleboba.foodPantryHelper.service.FoodChoiceService;
import com.pineappleboba.foodPantryHelper.service.FoodPickupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class FoodOrderController {
    private final FoodChoiceService foodChoiceService;
    private final FoodPickupService foodPickupService;

    public FoodOrderController(FoodChoiceService foodChoiceService, FoodPickupService foodPickupService) {
        this.foodChoiceService = foodChoiceService;
        this.foodPickupService = foodPickupService;
    }

    @GetMapping(value = "/foodpickups")
    @ResponseBody
    public List<FoodPickup> getAllFoodPickups() {
        return foodPickupService.getAllFoodPickups();
    }

    @GetMapping(value = "/foodchoices")
    @ResponseBody
    public List<FoodChoice> getFoodChoices() {
        return foodChoiceService.getFoodChoices();
    }

    @PostMapping(value="/submitOrder")
    public void submitOrder(@RequestBody FoodOrder foodOrder) {
        foodPickupService.createPickup(foodOrder);
        foodChoiceService.updateQuantities(foodOrder);
    }

    // deleteFoodPickup param pickupId
}
