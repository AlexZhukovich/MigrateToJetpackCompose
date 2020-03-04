package com.alexzh.coffeedrinks.feature.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexzh.coffeedrinks.R
import kotlinx.android.synthetic.main.item_coffee_drink_detail.view.*

class CoffeeDrinkDetailAdapter(
    private val values: List<String>
) : RecyclerView.Adapter<CoffeeDrinkDetailAdapter.CoffeeDrinkDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeDrinkDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_drink_detail, parent, false)
        return CoffeeDrinkDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeDrinkDetailViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    class CoffeeDrinkDetailViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(value: String) {
            itemView.detail_value.text = value
        }
    }
}