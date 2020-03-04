package com.alexzh.coffeedrinks.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.coffeedrinks.data.CoffeeDrinksRepository
import com.alexzh.coffeedrinks.feature.list.mapper.CoffeeDrinkUiModelMapper
import com.alexzh.coffeedrinks.feature.list.model.CoffeeDrinkUiModel
import kotlinx.coroutines.launch

class CoffeeDrinkListViewModel(
    private val repository: CoffeeDrinksRepository,
    private val mapper: CoffeeDrinkUiModelMapper
) : ViewModel() {
    private val coffeeDrinks = MutableLiveData<List<CoffeeDrinkUiModel>>()

    fun getCoffeeDrinks(): LiveData<List<CoffeeDrinkUiModel>> = coffeeDrinks

    fun loadCoffeeDrinks() {
        viewModelScope.launch {
            coffeeDrinks.value = mapper.map(repository.getCoffeeDrinks())
        }
    }

    fun updateFavouriteState(coffeeDrink: CoffeeDrinkUiModel) {
        viewModelScope.launch {
            val result = repository.updateFavouriteState(
                coffeeDrink.id,
                coffeeDrink.isFavourite.not()
            )
            if (result) {
                coffeeDrinks.value = coffeeDrinks.value?.map {
                    if (it.id == coffeeDrink.id) {
                        coffeeDrink.copy(isFavourite = coffeeDrink.isFavourite.not())
                    }
                    else it
                }
            }
        }
    }
}