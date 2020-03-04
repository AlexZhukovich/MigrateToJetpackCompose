package com.alexzh.coffeedrinks.feature.list.model

import androidx.annotation.DrawableRes

// TODO: check if all fields are needed
data class CoffeeDrinkUiModel(
    val id: Long,
    val name: String,
    @DrawableRes val imageRes: Int,
    val description: String,
    val ingredients: String,
    val isFavourite: Boolean
)