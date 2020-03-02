package com.alexzh.coffeedrinks.feature.detail.mapper

import com.alexzh.coffeedrinks.data.model.CoffeeDrink
import com.alexzh.coffeedrinks.feature.detail.model.CoffeeDrinkDetailUiModel

class CoffeeDrinkDetailUiModelMapper {

    fun map(coffeeDrink: CoffeeDrink): CoffeeDrinkDetailUiModel {
        return CoffeeDrinkDetailUiModel(
            coffeeDrink.id,
            coffeeDrink.name,
            coffeeDrink.imageRes,
            coffeeDrink.description,
            coffeeDrink.ingredients.toString(),
            coffeeDrink.isFavourite
        )
    }
}