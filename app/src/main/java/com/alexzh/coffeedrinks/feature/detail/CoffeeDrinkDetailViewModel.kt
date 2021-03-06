package com.alexzh.coffeedrinks.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.coffeedrinks.data.CoffeeDrinksRepository
import com.alexzh.coffeedrinks.feature.detail.mapper.CoffeeDrinkDetailUiModelMapper
import com.alexzh.coffeedrinks.feature.detail.model.CoffeeDrinkDetailUiModel
import kotlinx.coroutines.launch

class CoffeeDrinkDetailViewModel(
    private val repository: CoffeeDrinksRepository,
    private val mapper: CoffeeDrinkDetailUiModelMapper
) : ViewModel() {
    private val coffeeDrink = MutableLiveData<CoffeeDrinkDetailUiModel>()

    fun getCoffeeDrink(): LiveData<CoffeeDrinkDetailUiModel> = coffeeDrink

    // TODO: add error handling
    fun loadCoffeeDrink(id: Long) {
        viewModelScope.launch {
            coffeeDrink.value = repository.getCoffeeDrinkById(id)?.let { mapper.map(it) }
        }
    }

    fun updateFavouriteState() {
        viewModelScope.launch {
            val result = repository.updateFavouriteState(
                coffeeDrink.value?.id ?: -1,
                coffeeDrink.value?.isFavourite?.not() ?: false
            )
            if (result) {
                val oldValue = coffeeDrink.value?.isFavourite
                oldValue?.let {
                    coffeeDrink.value = coffeeDrink.value?.copy(isFavourite = it.not())
                }
            }
        }
    }
}