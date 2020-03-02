package com.alexzh.coffeedrinks.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexzh.coffeedrinks.data.CoffeeDrinksRepository
import com.alexzh.coffeedrinks.feature.detail.mapper.CoffeeDrinkDetailUiModelMapper
import com.alexzh.coffeedrinks.feature.detail.model.CoffeeDrinkDetailUiModel

class CoffeeDrinkDetailViewModel(
    private val repository: CoffeeDrinksRepository,
    private val mapper: CoffeeDrinkDetailUiModelMapper
) : ViewModel() {
    private val coffeeDrink = MutableLiveData<CoffeeDrinkDetailUiModel>()

    fun getCoffeeDrink(): LiveData<CoffeeDrinkDetailUiModel> = coffeeDrink

    // TODO: add error handling
    fun loadCoffeeDrink(id: Long) {
        coffeeDrink.value = repository.getCoffeeDrinkById(id)?.let { mapper.map(it) }
    }

    fun updateFavouriteState() {
        val result = repository.updateFavouriteState(
            coffeeDrink.value?.id ?: -1,
            coffeeDrink.value?.isFavourite?.not() ?: false
        )
        if (result) {
            coffeeDrink.value = coffeeDrink.value?.isFavourite?.not()?.let { coffeeDrink.value?.copy(isFavourite = it) }
        }
    }
}