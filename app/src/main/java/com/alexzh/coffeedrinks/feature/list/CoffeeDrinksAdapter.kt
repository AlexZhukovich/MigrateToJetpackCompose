package com.alexzh.coffeedrinks.feature.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.list.model.CoffeeDrinkUiModel
import kotlinx.android.synthetic.main.item_coffee_drink.view.*

// TODO: maybe use setters for click handlers
class CoffeeDrinksAdapter(
    private val isTablet: Boolean,
    private val itemClick: (CoffeeDrinkUiModel) -> Unit,
    private val favouriteItemClick: (CoffeeDrinkUiModel) -> Unit
) : RecyclerView.Adapter<CoffeeDrinksAdapter.CoffeeDrinksViewHolder>() {
    private val coffeeDrinks = mutableListOf<CoffeeDrinkUiModel>()

    fun setCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUiModel>) {
        this.coffeeDrinks.clear()
        this.coffeeDrinks.addAll(coffeeDrinks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeDrinksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_drink, parent, false)
        return CoffeeDrinksViewHolder(view, isTablet, itemClick, favouriteItemClick)
    }

    override fun onBindViewHolder(holder: CoffeeDrinksViewHolder, position: Int) {
        holder.bind(coffeeDrinks[position])
    }

    override fun getItemCount(): Int = coffeeDrinks.size

    class CoffeeDrinksViewHolder(
        view: View,
        private val isTablet: Boolean,
        val itemClick: (CoffeeDrinkUiModel) -> Unit,
        val favouriteClick: ((CoffeeDrinkUiModel) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(coffeeDrink: CoffeeDrinkUiModel) {
            with (coffeeDrink) {
                itemView.logo.setImageResource(imageRes)
                itemView.title.text = name
                itemView.ingredients.text = ingredients

                if (isTablet) {
                    itemView.favourite.visibility = View.GONE
                } else {
                    itemView.favourite.visibility = View.VISIBLE
                    itemView.favourite.setImageResource(
                        if (coffeeDrink.isFavourite) {
                            R.drawable.ic_action_favourite
                        } else {
                            R.drawable.ic_action_favourite_border
                        }
                    )
                    itemView.favourite.setOnClickListener { favouriteClick(coffeeDrink) }
                }

                itemView.setOnClickListener { itemClick(coffeeDrink) }
            }
        }
    }
}