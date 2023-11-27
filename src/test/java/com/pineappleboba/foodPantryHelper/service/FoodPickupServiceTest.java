package com.pineappleboba.foodPantryHelper.service;

import com.pineappleboba.foodPantryHelper.model.FoodOrder;
import com.pineappleboba.foodPantryHelper.model.FoodOrderItem;
import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import com.pineappleboba.foodPantryHelper.orm.FoodPickup;
import com.pineappleboba.foodPantryHelper.orm.FoodPickupRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

class FoodPickupServiceTest {

    @Mock
    FoodPickupRepository mockFoodPickupRepository;
    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void shutdown() throws Exception {
        closeable.close();
    }

    @Test
    void getAllFoodPickups() {
    }

    @Test
    void foodOrderToFoodPickup_success() {
        List<FoodOrderItem> foodItems = List.of(new FoodOrderItem(new FoodItem(1,1,"item1",0,0,0), 1));
        FoodOrder foodOrder = new FoodOrder("personIdentifier", 19, 19, "s", "pantryBag", foodItems);
        FoodPickup expected = new FoodPickup(0, "personIdentifier", 19, 19, "s", "pantryBag bag,(1) item1");
        FoodPickupService test = new FoodPickupService(mockFoodPickupRepository);

        // when
        test.createPickup(foodOrder);

        // then
        verify(mockFoodPickupRepository).save(expected);
    }

    // verify that multiple food items are correctly turned into a CSV style string
    @Test
    void foodOrderToFoodPickup_multipleFoodItems() {
        List<FoodOrderItem> foodItems = List.of(
                new FoodOrderItem(new FoodItem(1,1,"one",0,0,0), 1),
                new FoodOrderItem(new FoodItem(1,1,"two",0,0,0), 2),
                new FoodOrderItem(new FoodItem(1,1,"three",0,0,0), 1));
        FoodOrder foodOrder = new FoodOrder("personIdentifier", 19, 19, "s", "pantryBag", foodItems);
        FoodPickup expected = new FoodPickup(0, "personIdentifier", 19, 19, "s", "pantryBag bag,(1) one,(2) two,(1) three");
        FoodPickupService test = new FoodPickupService(mockFoodPickupRepository);

        // when
        test.createPickup(foodOrder);

        // then
        verify(mockFoodPickupRepository).save(expected);
    }

    // check that the choice of no pantryBag is handled correctly in the foodItems string
    @Test
    void foodOrderToFoodPickup_noPantryBag() {
        List<FoodOrderItem> foodItems = List.of(
                new FoodOrderItem(new FoodItem(1,1,"foodItem",0,0,0), 1));
        FoodOrder foodOrder = new FoodOrder("personIdentifier", 19, 19, "s", null, foodItems);
        FoodPickup expected = new FoodPickup(0, "personIdentifier", 19, 19, "s", "(1) foodItem");
        FoodPickupService test = new FoodPickupService(mockFoodPickupRepository);

        // when
        test.createPickup(foodOrder);

        // then
        verify(mockFoodPickupRepository).save(expected);
    }

    // verify that an order of just a food pantry bag is handled correctly
    @Test
    void foodOrderToFoodPickup_justABagPlease() {
        FoodOrder foodOrder = new FoodOrder("personIdentifier", 19, 19, "s", "pantryBag", Lists.newArrayList());
        FoodPickup expected = new FoodPickup(0, "personIdentifier", 19, 19, "s", "pantryBag bag");
        FoodPickupService test = new FoodPickupService(mockFoodPickupRepository);

        // when
        test.createPickup(foodOrder);

        // then
        verify(mockFoodPickupRepository).save(expected);
    }
}