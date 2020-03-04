package com.alexzh.coffeedrinks.data.model

import androidx.annotation.DrawableRes

data class CoffeeDrink(
    val id: Long,
    val name: String,
    @DrawableRes val imageRes: Int,
    val description: String,
    val ingredients: List<String>,
    val isFavourite: Boolean
)