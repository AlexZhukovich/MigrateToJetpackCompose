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

//        // TODO: change it
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        // Show the Up button in the action bar.
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
//            val fragment = ItemDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(
//                        ItemDetailFragment.ARG_ITEM_ID,
//                        intent.getStringExtra(ItemDetailFragment.ARG_ITEM_ID))
//                }
//            }

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
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, CoffeeDrinkListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}