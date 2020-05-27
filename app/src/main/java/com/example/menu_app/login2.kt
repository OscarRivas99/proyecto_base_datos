package com.example.menu_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login2.*

class login2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
       btn1.setOnClickListener {
           val intent = Intent(this,MainActivity::class.java)
           startActivity(intent)
       }
    }
}
