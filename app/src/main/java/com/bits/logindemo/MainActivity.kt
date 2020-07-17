package com.bits.logindemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val loginBtn = findViewById<View>(R.id.loginBtn)
        val registerTxt = findViewById<View>(R.id.regTxt)

        loginBtn.setOnClickListener {
            val emailTxt = findViewById<View>(R.id.emailTxt) as EditText
            val email = emailTxt.text.toString()
            val passwordTxt = findViewById<View>(R.id.passwordTxt) as EditText
            val password = passwordTxt.text.toString()

            if (!email.isEmpty() && !password.isEmpty()) {
                this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener( this, OnCompleteListener<AuthResult>
                { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, dashboard::class.java))
                        Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                    }
                })

            }else {
                Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
            }
        }

        registerTxt.setOnClickListener {
            intent=Intent(this,register::class.java)
            startActivity(intent)
        }

    }

}


