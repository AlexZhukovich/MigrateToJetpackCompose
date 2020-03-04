package com.alexzh.coffeedrinks.feature.detail.model

import androidx.annotation.DrawableRes

// TODO: check if all fields are needed
data class CoffeeDrinkDetailUiModel(
    val id: Long,
    val name: String,
    @DrawableRes val imageRes: Int,
    val description: String,
    val ingredients: String,
    val isFavourite: Boolean
)