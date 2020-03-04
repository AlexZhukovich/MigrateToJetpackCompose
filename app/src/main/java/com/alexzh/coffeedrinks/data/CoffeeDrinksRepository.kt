package com.alexzh.coffeedrinks.data

import com.alexzh.coffeedrinks.data.model.CoffeeDrink

interface CoffeeDrinksRepository {

    suspend fun getCoffeeDrinks(): List<CoffeeDrink>

    suspend fun getCoffeeDrinkById(id: Long): CoffeeDrink?

    suspend fun updateFavouriteState(id: Long, newFavouriteState: Boolean): Boolean
}