package com.example.menu_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.menu_app.R
import kotlinx.android.synthetic.main.activity_registro.*

class registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        button_3.setOnClickListener {
            val intent = Intent(this, login2::class.java)
            startActivity(intent)

        }
        button_4.setOnClickListener {
            val intent = Intent(this, login2::class.java)
            startActivity(intent)

        }
    }
}
