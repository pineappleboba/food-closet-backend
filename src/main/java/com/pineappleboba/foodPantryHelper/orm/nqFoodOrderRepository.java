package com.pineappleboba.foodPantryHelper.orm;

import jakarta.persistence.EntityManager;

//@ R epository
//@ T ransactional
public class nqFoodOrderRepository {

    //@ P ersistenceContext
    EntityManager entityManager;

    /*public List<FoodPickup> findAllFoodPickups() {
        TypedQuery<FoodPickup> namedQuery = entityManager.createNamedQuery("find_all_food_pickups", FoodPickup.class);
        return namedQuery.getResultList();
    }*/

}
