package com.example.cafeassignmentapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cafeassignmentapp.HomePageActivity

import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()


        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.MainButton_Register)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                attemptLogin(email, password)
            }
        }

        registerButton.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                    "Please enter both email and password",
                    Toast.LENGTH_SHORT
                ).show()

            return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Registration successful",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, task.exception?.message ?: "Registration failed", Toast.LENGTH_LONG).show()
            }
        }
      }
    }



    private fun attemptLogin(email: String, password: String) {
        Toast.makeText(this, "Trying Firebase login", Toast.LENGTH_SHORT).show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Login successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        task.exception?.message ?: "Login failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}


