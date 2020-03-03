package com.alexzh.coffeedrinks.feature.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.detail.CoffeeDrinkDetailActivity
import com.alexzh.coffeedrinks.feature.detail.CoffeeDrinkDetailFragment
import com.alexzh.coffeedrinks.feature.list.model.CoffeeDrinkUiModel
import kotlinx.android.synthetic.main.activity_coffee_drinks_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject

class CoffeeDrinkListActivity : AppCompatActivity() {

    private val viewModel: CoffeeDrinkListViewModel by inject()
    private val adapter: CoffeeDrinksAdapter by lazy {
        CoffeeDrinksAdapter(
            isTabletMode,
            ::onCoffeeDrinkClicked,
            ::onCoffeeDrinkFavouriteClicked
        )
    }
    private var isTabletMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_drinks_list)

        setSupportActionBar(toolbar)
        if (item_detail_container != null) {
            isTabletMode = true
        }
        setUpCoffeeDrinkList()

        viewModel.getCoffeeDrinks().observe(this, Observer {
            showCoffeeDrinks(it)
        })

        viewModel.loadCoffeeDrinks()
    }

    private fun setUpCoffeeDrinkList() {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )
    }

    private fun showCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUiModel>) {
        adapter.setCoffeeDrinks(coffeeDrinks)
    }

    private fun onCoffeeDrinkClicked(coffeeDrink: CoffeeDrinkUiModel) {
        if (isTabletMode) {
            val fragment = CoffeeDrinkDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(CoffeeDrinkDetailFragment.COFFEE_DRINK_ID, coffeeDrink.id)
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this@CoffeeDrinkListActivity, CoffeeDrinkDetailActivity::class.java).apply {
                putExtra(CoffeeDrinkDetailFragment.COFFEE_DRINK_ID, coffeeDrink.id)
            }
            startActivity(intent)
        }
    }

    private fun onCoffeeDrinkFavouriteClicked(coffeeDrink: CoffeeDrinkUiModel) {
        viewModel.updateFavouriteState(coffeeDrink)
    }
}