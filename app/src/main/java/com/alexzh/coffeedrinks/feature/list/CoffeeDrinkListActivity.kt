package com.alexzh.coffeedrinks.feature.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.detail.CoffeeDrinkDetailActivity
import com.alexzh.coffeedrinks.feature.detail.CoffeeDrinkDetailFragment
import kotlinx.android.synthetic.main.activity_coffee_drinks_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.koin.android.ext.android.inject

class CoffeeDrinkListActivity : AppCompatActivity() {

    private val viewModel: CoffeeDrinkListViewModel by inject()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_drinks_list)

        setSupportActionBar(toolbar)

        if (item_detail_container != null) {
            twoPane = true
        }

        // TODO: item_list == recyclerView
        val adapter = CoffeeDrinksAdapter(
            { coffeeDrinkUiModel ->
                if (twoPane) {
                    val fragment = CoffeeDrinkDetailFragment().apply {
                        arguments = Bundle().apply {
                            putLong(CoffeeDrinkDetailFragment.COFFEE_DRINK_ID, coffeeDrinkUiModel.id)
                        }
                    }
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(this@CoffeeDrinkListActivity, CoffeeDrinkDetailActivity::class.java).apply {
                        putExtra(CoffeeDrinkDetailFragment.COFFEE_DRINK_ID, coffeeDrinkUiModel.id)
                    }
                    startActivity(intent)
                }
            },
            {favouriteItemClick ->
                // TODO: implement changing favourite state
            }
        )
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )

        viewModel.getCoffeeDrinks().observe(this, Observer {
            adapter.setCoffeeDrinks(it)
        })

        viewModel.loadCoffeeDrinks()
    }
}