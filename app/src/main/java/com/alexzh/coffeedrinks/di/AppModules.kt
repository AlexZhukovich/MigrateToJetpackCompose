package com.alexzh.coffeedrinks.di

import com.alexzh.coffeedrinks.data.CoffeeDrinksRepository
import com.alexzh.coffeedrinks.data.RuntimeCoffeeDrinkRepository
import com.alexzh.coffeedrinks.feature.detail.CoffeeDrinkDetailViewModel
import com.alexzh.coffeedrinks.feature.detail.mapper.CoffeeDrinkDetailUiModelMapper
import com.alexzh.coffeedrinks.feature.list.CoffeeDrinkListViewModel
import com.alexzh.coffeedrinks.feature.list.mapper.CoffeeDrinkUiModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<CoffeeDrinksRepository> { RuntimeCoffeeDrinkRepository() }
}

val coffeeDrinkListModule = module {
    factory { CoffeeDrinkUiModelMapper() }
    viewModel { CoffeeDrinkListViewModel(repository = get(), mapper = get()) }
}

val coffeeDrinkDetailModule = module {
    factory { CoffeeDrinkDetailUiModelMapper() }
    viewModel { CoffeeDrinkDetailViewModel(repository = get(), mapper = get()) }
}