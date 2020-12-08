package com.example.stompcovidrumors

import android.R.attr.password
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class CreateAccountActivity : Activity(){

    companion object {
        const val TAG = "Example-FirebaseRealtimeDatabase"
        const val AUTHOR_NAME = "course.examples.firebase.myhomelibrary.authorname"
        const val AUTHOR_ID = "course.examples.firebase.myhomelibrary.authorid"
    }

    private lateinit var databaseNewUser: DatabaseReference
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        mAuth = FirebaseAuth.getInstance()
        val signUp = findViewById<Button>(R.id.sign_up_button)

        signUp.setOnClickListener{ createNewUser() }

    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
    }

    private fun createNewUser() {
        val email = findViewById<TextView>(R.id.create_account_username).text.toString()
        val password = findViewById<TextView>(R.id.create_account_password).text.toString()
        val homeActivityIntent = Intent(this, HomeActivity::class.java)
        val validator = Validators()

        if (!validator.validEmail(email)) {
            Toast.makeText(this, "Must be a valid email", Toast.LENGTH_LONG).show()
            return
        }

        if (!validator.validPassword(password)) {
            Toast.makeText(this, "Passwords should be at least 4 characters with 1 letter and 1 number", Toast.LENGTH_LONG).show()
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    homeActivityIntent.putExtra("user", email)
                    startActivity(homeActivityIntent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}