package com.alexzh.coffeedrinks.data

import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.data.model.CoffeeDrink
import kotlinx.coroutines.delay

class RuntimeCoffeeDrinkRepository : CoffeeDrinksRepository {
    private val coffeeDrinks: MutableList<CoffeeDrink> = initCoffeeDrinks()

    override suspend fun getCoffeeDrinks(): List<CoffeeDrink> {
        delay(1_000)
        return coffeeDrinks
    }

    override suspend fun getCoffeeDrinkById(id: Long): CoffeeDrink? {
        delay(1_000)
        return coffeeDrinks.firstOrNull { it.id == id }
    }

    override suspend fun updateFavouriteState(
        id: Long,
        newFavouriteState: Boolean
    ): Boolean {
        delay(500)
        val index = coffeeDrinks.indexOfFirst { it.id == id }
        if (index >= 0 && index < coffeeDrinks.size) {
            coffeeDrinks[index] = coffeeDrinks[index].copy(isFavourite = newFavouriteState)
            return true
        }
        return false
    }

    private fun initCoffeeDrinks(): MutableList<CoffeeDrink> {
        return mutableListOf(
            CoffeeDrink(
                id = 1L,
                name = "Americano",
                imageRes = R.drawable.americano_small,
                description = "Americano is a type of coffee drink prepared by diluting an espresso with hot water, giving it a similar strength to, but different flavour from, traditionally brewed coffee. ",
                ingredients = listOf(
                    "Espresso",
                    "Water"
                ),
                isFavourite = true
            ),
            CoffeeDrink(
                id = 2L,
                name = "Cappuccino",
                imageRes = R.drawable.cappuccino_small,
                description = "A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam.",
                ingredients = listOf(
                    "Espresso",
                    "Steamed milk foam"
                ),
                isFavourite = true
            ),
            CoffeeDrink(
                id = 3L,
                name = "Espresso",
                imageRes = R.drawable.espresso_small,
                description = "Espresso is coffee of Italian origin, brewed by forcing a small amount of nearly boiling water under pressure (expressing) through finely-ground coffee beans.",
                ingredients =
                listOf(
                    "Ground coffee",
                    "Water"
                ),
                isFavourite = true
            ),
            CoffeeDrink(
                id = 4L,
                name = "Espresso Macchiato",
                imageRes = R.drawable.espresso_macchiato_small,
                description = "Espresso Macchiato is a coffee beverage (a single or double espresso topped with a dollop of heated, foamed milk).",
                ingredients = listOf(
                    "Espresso",
                    "Foamed milk"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 5L,
                name = "Frappino",
                imageRes = R.drawable.frappino_small,
                description = "Frappino is a blended coffee drinks. It consists of coffee base, blended with ice and other various ingredients, usually topped with whipped cream.",
                ingredients = listOf(
                    "Espresso",
                    "Cold milk",
                    "Sugar",
                    "Ice cubes",
                    "Irish Cream flavoured syrup",
                    "Whipped cream",
                    "Chocolate sauce"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 6L,
                name = "Iced Mocha",
                imageRes = R.drawable.iced_mocha_small,
                description = "Iced Mocha is a coffee beverage. It based on Espresso and chocolate syrup with cold milk, foam and whipped cream or ice cream.",
                ingredients = listOf(
                    "Cold coffee",
                    "Milk",
                    "Chocolate syrup",
                    "Whipped cream",
                    "Ice cream"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 7L,
                name = "Irish coffee",
                imageRes = R.drawable.irish_coffee_small,
                description = "Irish coffee is a cocktail consisting of hot coffee, Irish whiskey, and sugar stirred, and topped with cream.",
                ingredients = listOf(
                    "Irish whiskey",
                    "Hot strong brewed coffee",
                    "Heavy whipping cream",
                    "Sugar",
                    "Creme de menthe liqueur"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 8L,
                name = "Latte",
                imageRes = R.drawable.latte_small,
                description = "A latte is a coffee drink made with espresso and steamed milk.",
                ingredients = listOf(
                    "Espresso",
                    "Steamed milk"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 9L,
                name = "Latte Macchiato",
                imageRes = R.drawable.latte_macchiato_small,
                description = "Latte Macchiato is a coffee beverage; the name literally means stained milk.",
                ingredients = listOf(
                    "Espresso",
                    "Milk",
                    "Milk foam",
                    "Flavoured coffee syrup"
                ),
                isFavourite = false
            ),
            CoffeeDrink(
                id = 10L,
                name = "Mocha",
                imageRes = R.drawable.mocha_small,
                description = "A Mocha, also called mocaccino, is a chocolate-flavored variant of a Latte.",
                ingredients = listOf(
                    "Espresso",
                    "Chocolate flavoring"
                ),
                isFavourite = false
            )
        )
    }
}