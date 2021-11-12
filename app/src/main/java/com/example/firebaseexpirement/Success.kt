package com.example.firebaseexpirement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.annotation.NonNull
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


class Success : AppCompatActivity() {
    private lateinit var logout:Button
    private lateinit var emailView:TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        val user = Firebase.auth.currentUser
        emailView=findViewById(R.id.email)
        logout=findViewById(R.id.logout)
        var email:String?=null
        user?.let {
            // Name, email address, and profile photo Url

           email = user.email
            if(email==null || email==""){
                email=user.displayName
            }

        }

        emailView.setText("Welcome $email")
        logout.setOnClickListener {
            Firebase.auth.signOut()
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_client_id))
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut()
            LoginManager.getInstance().logOut()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}