package com.pineappleboba.foodPantryHelper.service;

import com.pineappleboba.foodPantryHelper.orm.FoodChoice;
import com.pineappleboba.foodPantryHelper.orm.FoodChoiceRepository;
import com.pineappleboba.foodPantryHelper.orm.FoodItem;
import com.pineappleboba.foodPantryHelper.orm.FoodItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class FoodChoiceServiceTest {

    @Mock
    FoodChoiceRepository mockFoodChoiceRepository;

    @Mock
    FoodItemRepository mockFoodItemRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void success() {
        // given
        FoodChoice unfilteredFoodChoice = new FoodChoice(1,
                List.of(new FoodItem(1, 1, "a", 1, 1, 10),
                        new FoodItem(2, 1, "b", 1, 1, 10),
                        new FoodItem(3, 1, "c", 1, 1, 10),
                        new FoodItem(4, 1, "d", 1, 1, 10)
                ));
        when(mockFoodChoiceRepository.findAll()).thenReturn(List.of(unfilteredFoodChoice));
        FoodChoiceService testClass = new FoodChoiceService(mockFoodChoiceRepository, mockFoodItemRepository);

        // when
        List<FoodChoice> result = testClass.getFoodChoices();

        // then
        assertEquals(4, result.get(0).getFoodItemsInChoice().size());
    }
}