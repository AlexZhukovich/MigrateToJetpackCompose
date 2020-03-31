package com.alexzh.coffeedrinks.feature.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.list.model.CardType
import com.alexzh.coffeedrinks.feature.list.model.CoffeeDrinkUiModel
import kotlinx.android.synthetic.main.item_coffee_drink.view.*

// TODO: maybe use setters for click handlers
class CoffeeDrinksAdapter(
    private val isTablet: Boolean,
    private val itemClick: (CoffeeDrinkUiModel) -> Unit,
    private val favouriteItemClick: (CoffeeDrinkUiModel) -> Unit
) : RecyclerView.Adapter<CoffeeDrinksAdapter.CoffeeDrinksViewHolder>() {
    private val coffeeDrinks = mutableListOf<CoffeeDrinkUiModel>()
    private var cardType: CardType = CardType.DEFAULT_CARD

    fun setCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUiModel>) {
        val diffResult = DiffUtil.calculateDiff(
            CoffeeDrinksCallback(this.coffeeDrinks, coffeeDrinks)
        )
        this.coffeeDrinks.clear()
        this.coffeeDrinks.addAll(coffeeDrinks)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setCardType(cardType: CardType) {
        this.cardType = cardType
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeDrinksViewHolder {
        val view = if (cardType == CardType.DEFAULT_CARD) {
            LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_drink, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_drink_extended, parent, false)
        }
        return CoffeeDrinksViewHolder(view, cardType, isTablet, itemClick, favouriteItemClick)
    }

    override fun onBindViewHolder(holder: CoffeeDrinksViewHolder, position: Int) {
        holder.bind(coffeeDrinks[position])
    }

    override fun getItemCount(): Int = coffeeDrinks.size

    class CoffeeDrinksViewHolder(
        view: View,
        private val cardType: CardType,
        private val isTablet: Boolean,
        val itemClick: (CoffeeDrinkUiModel) -> Unit,
        val favouriteClick: ((CoffeeDrinkUiModel) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(coffeeDrink: CoffeeDrinkUiModel) {
            with (coffeeDrink) {
                itemView.logo.setImageResource(imageRes)
                itemView.title.text = name
                itemView.additional.text = if (cardType == CardType.DEFAULT_CARD) {
                    ingredients
                } else {
                    description
                }

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

class CoffeeDrinksCallback(
    private val oldCoffeeDrinks: List<CoffeeDrinkUiModel>,
    private val newCoffeeDrinks: List<CoffeeDrinkUiModel>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoffeeDrinks[oldItemPosition].id == newCoffeeDrinks[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldCoffeeDrinks.size

    override fun getNewListSize(): Int = newCoffeeDrinks.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoffeeDrinks[oldItemPosition] == newCoffeeDrinks[newItemPosition]
    }
}