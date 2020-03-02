package com.alexzh.coffeedrinks.data

import com.alexzh.coffeedrinks.data.model.CoffeeDrink

interface CoffeeDrinksRepository {

    fun getCoffeeDrinks(): List<CoffeeDrink>

    fun getCoffeeDrinkById(id: Long): CoffeeDrink?

    fun updateFavouriteState(id: Long, newFavouriteState: Boolean): Boolean
}