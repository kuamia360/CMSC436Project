package com.example.stompcovidrumors

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.app.Activity
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUp = findViewById<Button>(R.id.sign_up_button)
        val logIn = findViewById<Button>(R.id.log_in)


        logIn.setOnClickListener{goToLogin()}

        signUp.setOnClickListener{goToCreateAccount()}
    }

    private fun goToLogin() {
        val loginIntent = Intent(this, LogInActivity::class.java)
        startActivity(loginIntent)
    }

     private fun goToCreateAccount() {
        val createAccountIntent = Intent(this, CreateAccountActivity::class.java)
        startActivity(createAccountIntent)
    }
}