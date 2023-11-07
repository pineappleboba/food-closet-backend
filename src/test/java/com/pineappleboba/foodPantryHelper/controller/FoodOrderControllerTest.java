package com.pineappleboba.foodPantryHelper.controller;

import com.pineappleboba.foodPantryHelper.service.FoodPickupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FoodOrderController.class)
class FoodOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FoodPickupService foodPickupService;

    @Test
    // verify the REST endpoint and response
    // verify that the service was called
    // TODO check the result
    void getAllFoodPickups() throws Exception {
        MvcResult result = mockMvc.perform(get("/foodpickups")).andExpect(status().isOk()).andReturn();
        verify(foodPickupService).getAllFoodPickups();
    }
}