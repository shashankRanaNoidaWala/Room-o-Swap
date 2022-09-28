package com.bottlerunner.room_o_swap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.bottlerunner.room_o_swap.data.UserApna
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        actionBar?.hide()

        Handler().postDelayed({
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        },1000)
    }
}