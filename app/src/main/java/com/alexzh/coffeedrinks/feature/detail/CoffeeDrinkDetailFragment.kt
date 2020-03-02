package com.alexzh.coffeedrinks.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alexzh.coffeedrinks.R
import kotlinx.android.synthetic.main.fragment_coffee_drinks_detail.view.*
import org.koin.android.ext.android.inject

class CoffeeDrinkDetailFragment : Fragment() {
    private val viewModel: CoffeeDrinkDetailViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_coffee_drinks_detail, container, false)

        (activity as AppCompatActivity).setSupportActionBar(rootView.detail_toolbar)

        if (requireActivity().findViewById<View>(R.id.recyclerView) == null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        viewModel.getCoffeeDrink().observe(viewLifecycleOwner, Observer { coffeeDrink ->
            // TODO: fix it
            (activity as AppCompatActivity).supportActionBar?.title = coffeeDrink.name
            rootView.logoDetails.setImageResource(coffeeDrink.imageRes)
            val values = listOf(
                coffeeDrink.description,
                coffeeDrink.ingredients
            )

            val adapter = CoffeeDrinkDetailAdapter(values)
            rootView.detail_recycler_view.adapter = adapter

            rootView.favourite_fab.setImageResource(
                if (coffeeDrink.isFavourite) {
                    R.drawable.ic_action_favourite
                } else {
                    R.drawable.ic_action_favourite_border
                }
            )
        })

        arguments?.let {
            if (it.containsKey(COFFEE_DRINK_ID)) {
                viewModel.loadCoffeeDrink(it.getLong(COFFEE_DRINK_ID, -1))
            }
        }

        rootView.favourite_fab.setOnClickListener {
            viewModel.updateFavouriteState()
        }
        return rootView
    }

    companion object {
        const val COFFEE_DRINK_ID = "coffee_drink_id"
    }
}