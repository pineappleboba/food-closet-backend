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
        List<FoodChoice> result = testClass.getFoodChoices(FoodChoiceService.LARGE_FAMILY_MINIMUM);

        // then
        assertEquals(4, result.get(0).getFoodItemsInChoice().size());
    }

    // out of stock test - verify that items removed where quantity is zero
    @Test
    void filterOutZeroQuantityItems() {
        // given
        FoodChoice unfilteredFoodChoice = new FoodChoice(1,
                List.of(new FoodItem(1, 1, "x", 1, 1, 0)));
        when(mockFoodChoiceRepository.findAll()).thenReturn(List.of(unfilteredFoodChoice));
        FoodChoiceService testClass = new FoodChoiceService(mockFoodChoiceRepository, mockFoodItemRepository);

        // when
        List<FoodChoice> result = testClass.getFoodChoices(FoodChoiceService.LARGE_FAMILY_MINIMUM);

        // then
        assertTrue(result.isEmpty());
    }

    // large family test - verify that items removed where largeFamilyQuantity is zero
    @Test
    void getFoodChoicesForLargeFamily() {
        // given
        FoodChoice unfilteredFoodChoice = new FoodChoice(1,
                List.of(new FoodItem(1, 1, "x", 0, 1, 1)));
        when(mockFoodChoiceRepository.findAll()).thenReturn(List.of(unfilteredFoodChoice));
        FoodChoiceService testClass = new FoodChoiceService(mockFoodChoiceRepository, mockFoodItemRepository);

        // when
        List<FoodChoice> result = testClass.getFoodChoices(FoodChoiceService.LARGE_FAMILY_MINIMUM);

        // then
        assertTrue(result.isEmpty());
    }

    // small family test - verify that items removed where smallFamilyQuantity is zero
    @Test
    void getFoodChoicesForSmallFamily() {
        // given
        FoodChoice unfilteredFoodChoice = new FoodChoice(1,
                List.of(new FoodItem(1, 1, "x", 20, 0, 1)));
        when(mockFoodChoiceRepository.findAll()).thenReturn(List.of(unfilteredFoodChoice));
        FoodChoiceService testClass = new FoodChoiceService(mockFoodChoiceRepository, mockFoodItemRepository);

        // when
        List<FoodChoice> result = testClass.getFoodChoices(1);

        // then
        assertTrue(result.isEmpty());
    }

    // able to handle a reduction where a list goes from four to one
    @Test
    void getFoodChoicesReduced() {
        // given
        FoodChoice unfilteredFoodChoice = new FoodChoice(1,
                List.of(new FoodItem(1, 1, "invalid", 1, 0, 10),
                        new FoodItem(2, 1, "invalid", 1, 0, 10),
                        new FoodItem(3, 1, "invalid", 1, 1, 0),
                        new FoodItem(4, 1, "yes!", 1, 1, 10)
                        ));
        when(mockFoodChoiceRepository.findAll()).thenReturn(List.of(unfilteredFoodChoice));
        FoodChoiceService testClass = new FoodChoiceService(mockFoodChoiceRepository, mockFoodItemRepository);

        // when
        List<FoodChoice> result = testClass.getFoodChoices(1);

        // then
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getFoodItemsInChoice().size());
        assertEquals("yes!", result.get(0).getFoodItemsInChoice().get(0).getName());
    }
}