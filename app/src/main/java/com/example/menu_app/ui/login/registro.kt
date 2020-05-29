package com.example.menu_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.menu_app.R
import kotlinx.android.synthetic.main.activity_registro.*

class registro : AppCompatActivity() {
    val b:Bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        button_3.setOnClickListener {

            val intent = Intent(this, login2::class.java)
            val user:String=txt_usuario_add.text.toString();
            val pass:String=txt_pass_add.text.toString();

            b.putString("user",user)
            b.putString("pass",pass)
            intent.putExtras(b)
            startActivity(intent)

        }
        button_4.setOnClickListener {
            val intent = Intent(this, login2::class.java)
            startActivity(intent)

        }
    }
}
