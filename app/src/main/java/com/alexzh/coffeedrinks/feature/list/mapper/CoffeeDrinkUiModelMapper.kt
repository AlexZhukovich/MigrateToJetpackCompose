package com.alexzh.coffeedrinks.feature.list.mapper

import com.alexzh.coffeedrinks.data.model.CoffeeDrink
import com.alexzh.coffeedrinks.feature.list.model.CoffeeDrinkUiModel

class CoffeeDrinkUiModelMapper {

    fun map(coffeeDrinks: List<CoffeeDrink>) : List<CoffeeDrinkUiModel> {
        return coffeeDrinks.map { map(it) }
    }

    fun map(coffeeDrink: CoffeeDrink) : CoffeeDrinkUiModel {
        return CoffeeDrinkUiModel(
            coffeeDrink.id,
            coffeeDrink.name,
            coffeeDrink.imageRes,
            coffeeDrink.description,
            coffeeDrink.ingredients.toString(),
            coffeeDrink.isFavourite
        )
    }
}