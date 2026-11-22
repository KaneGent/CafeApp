package com.example.cafeassignmentapp

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MenuActivity : AppCompatActivity() {

    private lateinit var courseGRV: GridView
    private var menuList = ArrayList<MenueItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        Toast.makeText(this, "Menu Page Opened", Toast.LENGTH_LONG).show()

        courseGRV = findViewById(R.id.idGRV)

        val db = FirebaseFirestore.getInstance()

        db.collection("MenueItems")
            .get()
            .addOnSuccessListener { documents ->

                Toast.makeText(
                    this,
                    "Found ${documents.size()} docs",
                    Toast.LENGTH_LONG
                ).show()

                menuList.clear()

                for (document in documents) {

                    val name = document.getString("Name") ?: ""
                    val price = document.getDouble("Price") ?: 0.0

                    menuList.add(
                        MenueItem(
                            name,
                            "£$price",
                            R.drawable.coffie_cup_image
                        )
                    )
                }

                val adapter = GridRVAdapter(menuList, this)
                courseGRV.adapter = adapter
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Failed to load menu items",
                    Toast.LENGTH_LONG
                ).show()
            }

        courseGRV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->

                Toast.makeText(
                    applicationContext,
                    "${menuList[position].name} selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
