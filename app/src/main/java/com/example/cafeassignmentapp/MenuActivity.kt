package com.example.cafeassignmentapp

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var courseGRV: GridView
    private lateinit var courseList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Toast.makeText(this, "Menu Page Opened", Toast.LENGTH_LONG).show()

        // Initializing variables of grid view with their ids.
        courseGRV = findViewById(R.id.idGRV)
        courseList = ArrayList<GridViewModal>()

        // Adding data to our course list with image and course name.
        courseList = courseList + GridViewModal("Coffee", R.drawable.coffie_cup_image)
        courseList = courseList + GridViewModal("Espresso", R.drawable.coffie_cup_image)
        courseList = courseList + GridViewModal("Cappuccino", R.drawable.coffie_cup_image)
        courseList = courseList + GridViewModal("Latte", R.drawable.coffie_cup_image)
        courseList = courseList + GridViewModal("Mocha", R.drawable.coffie_cup_image)

        // Initializing our course adapter and passing course list and context.
        val courseAdapter = GridRVAdapter(courseList, this)

        // Setting adapter to our grid view.
        courseGRV.adapter = courseAdapter

        // Adding on item click listener for our grid view.
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext,
                courseList[position].courseName + " selected",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
