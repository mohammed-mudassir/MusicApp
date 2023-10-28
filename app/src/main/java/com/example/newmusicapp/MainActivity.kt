package com.example.newmusicapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.MenuInflater
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.newmusicapp.databinding.ActivityMainBinding
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    // View Binding
    var binding: ActivityMainBinding? = null
    lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // getting the employee list by
        // calling getEmployeeData method

        val emplist = Constants.getEmployeeData()
        binding?.rvItemsList?.layoutManager = LinearLayoutManager(this)
        binding?.rvItemsList?.setHasFixedSize(true)

        // Creating an instance of the
        // adapter and passing emplist to it
        itemAdapter= ItemAdapter(emplist);

        // Assign ItemAdapter instance to our RecyclerView
        binding?.rvItemsList?.adapter = itemAdapter
        itemAdapter.notifyDataSetChanged()

        // Applying OnClickListener to our Adapter
        itemAdapter.setOnClickListener(object :
            ItemAdapter.OnClickListener {
            override fun onClick(position: Int, model: MusicList) {
                val intent = Intent(this@MainActivity, MusicPlayer::class.java)
                // Passing the data to the
                // EmployeeDetails Activity
                intent.putExtra(NEXT_SCREEN, model)
                startActivity(intent)
            }
        })
    }

    companion object {
        val NEXT_SCREEN = "details_screen"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_item_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.getActionView() as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<MusicList> = ArrayList()
        val MusicList:ArrayList<MusicList>     =ArrayList()


        // running a for loop to compare elements.


        for (item in MusicList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name. toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            itemAdapter.filterList(filteredlist)
        }
    }

}













