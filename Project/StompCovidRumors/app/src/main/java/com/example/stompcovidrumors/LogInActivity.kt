package com.example.stompcovidrumors

import android.R.attr.password
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class LogInActivity : Activity() {

    companion object {
        const val TAG = "Example-FirebaseRealtimeDatabase"
        const val AUTHOR_NAME = "course.examples.firebase.myhomelibrary.authorname"
        const val AUTHOR_ID = "course.examples.firebase.myhomelibrary.authorid"
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)

        mAuth = FirebaseAuth.getInstance()
        val login = findViewById<Button>(R.id.login_button)

        login.setOnClickListener{ userLogin() }
    }

    private fun userLogin() {
        val email = findViewById<TextView>(R.id.login_username).text.toString()
        val password = findViewById<TextView>(R.id.login_password).text.toString()
        val homeActivityIntent = Intent(this, HomeActivity::class.java)

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = mAuth!!.currentUser
                    homeActivityIntent.putExtra("user", user)
                    startActivity(homeActivityIntent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }
}