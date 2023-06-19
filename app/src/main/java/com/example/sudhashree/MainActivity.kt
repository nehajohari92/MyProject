package com.example.sudhashree

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sudhashree.databinding.ActivityMainBinding
import com.example.sudhashree.db.Mobile
import com.example.sudhashree.db.SudhaShreeDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var database: SudhaShreeDB
    lateinit var viewModel :MobileViewModel
    val sharedPrefFile = "SSsp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        database= SudhaShreeDB.getDBInstance(this)

        val modelObject = ArrayList<Mobile>()

        modelObject.add(Mobile(mobileModel = "oppo", mobilePrice = "100", quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "Realme", mobilePrice = "200",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "Samsung", mobilePrice = "300",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "MI", mobilePrice = "400",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "I-phone", mobilePrice = "500",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "Nokia", mobilePrice = "600",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "Motorola", mobilePrice = "700",quantityBlack = "10", quantityWhite = "20"))
        modelObject.add(Mobile(mobileModel = "Oneplus", mobilePrice = "800",quantityBlack = "10", quantityWhite = "20"))

        GlobalScope.launch {
            try {
                database.mobiledao().insertMobile(modelObject)
            } catch (exception: SQLiteConstraintException) {
                // Handle the exception (e.g., show an error message)
            }
        }

        viewModel= ViewModelProvider(this).get(MobileViewModel::class.java)
        viewModel.getAllData().observe(this, Observer {
            datalist ->
            binding.recyclerview.adapter = MyAdapter(datalist,this)
            binding.recyclerview.layoutManager= LinearLayoutManager(this)
        })

        binding.fab.setOnClickListener{
            val intent = Intent(this,AddNewData::class.java)
            this.startActivity(intent)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                performSearch(newText)
                return true
            }
        })
    }

    private fun performSearch(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val searchResults = database.mobiledao().searchByName(query)
            launch(Dispatchers.Main) {
                binding.recyclerview.adapter = MyAdapter(searchResults, this@MainActivity)
                binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

}