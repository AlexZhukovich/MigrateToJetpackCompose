package com.alexzh.coffeedrinks.feature.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.detail.model.CoffeeDrinkDetailUiModel
import kotlinx.android.synthetic.main.fragment_coffee_drinks_detail.*
import org.koin.android.ext.android.inject

class CoffeeDrinkDetailFragment : Fragment(R.layout.fragment_coffee_drinks_detail) {
    companion object {
        const val COFFEE_DRINK_ID = "coffee_drink_id"
        private const val INVALID_COFFEE_DRINK_ID = -1L
    }

    private val viewModel: CoffeeDrinkDetailViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()

        arguments?.let {
            if (it.containsKey(COFFEE_DRINK_ID)) {
                viewModel.loadCoffeeDrink(it.getLong(COFFEE_DRINK_ID, INVALID_COFFEE_DRINK_ID))
            }
        }

        viewModel.getCoffeeDrink().observe(viewLifecycleOwner, Observer {
            showCoffeeDrink(it)
        })

        favourite_fab.setOnClickListener {
            viewModel.updateFavouriteState()
        }
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(detail_toolbar)

        if (requireActivity().findViewById<View>(R.id.recyclerView) == null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showCoffeeDrink(coffeeDrink: CoffeeDrinkDetailUiModel) {
        (activity as AppCompatActivity).supportActionBar?.title = coffeeDrink.name
        logoDetails.setImageResource(coffeeDrink.imageRes)
        val values = listOf(
            coffeeDrink.description,
            coffeeDrink.ingredients
        )

        val adapter = CoffeeDrinkDetailAdapter(values)
        detail_recycler_view.adapter = adapter

        favourite_fab.setImageResource(
            if (coffeeDrink.isFavourite) {
                R.drawable.ic_action_favourite
            } else {
                R.drawable.ic_action_favourite_border
            }
        )
    }
}