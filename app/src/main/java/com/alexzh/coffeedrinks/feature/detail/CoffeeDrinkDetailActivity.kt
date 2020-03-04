package com.alexzh.coffeedrinks.feature.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alexzh.coffeedrinks.R
import com.alexzh.coffeedrinks.feature.list.CoffeeDrinkListActivity

class CoffeeDrinkDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_drinks_detail)

        if (savedInstanceState == null) {
            val fragment = CoffeeDrinkDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(
                        CoffeeDrinkDetailFragment.COFFEE_DRINK_ID,
                        intent.getLongExtra(CoffeeDrinkDetailFragment.COFFEE_DRINK_ID, -1))
                }
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, CoffeeDrinkListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}