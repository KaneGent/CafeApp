package com.example.cafeassignmentapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_menu)

       val gridMenu = findViewById<GridView>(R.id.gridMenu)
        val menuItems = arrayOf("Latte", "Tea", "Cappuccino", "Mocha", "Americano", "Espresso", "Macchiato")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems)

        gridMenu.adapter = adapter

    }
}