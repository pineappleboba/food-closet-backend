package com.pineappleboba.foodPantryHelper.orm;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FoodChoiceRepository extends JpaRepository<FoodChoice, Integer> {
}
