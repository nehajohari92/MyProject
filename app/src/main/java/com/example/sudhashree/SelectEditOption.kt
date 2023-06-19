package com.example.sudhashree

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.sudhashree.ui.main.SectionsPagerAdapter
import com.example.sudhashree.databinding.ActivitySelectEditOptionBinding

class SelectEditOption : AppCompatActivity() {

private lateinit var binding: ActivitySelectEditOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivitySelectEditOptionBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val modelName=intent.getStringExtra("Modelname")
        val modelPrice=intent.getStringExtra("Modelprice")
        val modelID= intent.getIntExtra("ModelID",0)
        val Quantity_black = intent.getStringExtra("Quantity_BLACK")
        val Quantity_white = intent.getStringExtra("Quantity_White")

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,
            modelName,
        modelPrice,Quantity_black,Quantity_white,modelID)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}