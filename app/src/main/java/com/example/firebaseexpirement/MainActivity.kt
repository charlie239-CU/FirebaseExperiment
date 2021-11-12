package com.example.firebaseexpirement

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailField:EditText
    private lateinit var passField:EditText
    private lateinit var login:Button
    private lateinit var signup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailField=findViewById(R.id.email)
        auth = Firebase.auth
        passField=findViewById(R.id.password)
        login=findViewById(R.id.login)
        signup=findViewById(R.id.signup)
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent= Intent(this,Success::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val email=emailField.text.toString()
            val password=passField.text.toString()
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this,"Empty Fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent=Intent(this,Success::class.java)
                        startActivity(intent)
                       // updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                      //  updateUI(null)
                    }
                }
        }

        signup.setOnClickListener {
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }
}