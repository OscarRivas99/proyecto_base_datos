package com.example.menu_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.menu_app.MainActivity
import com.example.menu_app.R
import kotlinx.android.synthetic.main.activity_login2.*

class login2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

       btn1.setOnClickListener {
           val user_name = usuario.text.trim().toString();
           val password = pass.text.trim().toString();
           if ((user_name.equals("root",true) && (password.equals("root", true))) ){
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
           }else{
               Toast.makeText(this, "usuario o contraseña incorrectas"+ usuario.text.toString() ,+ Toast.LENGTH_LONG).show()
           }

       }
        button2.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)

        }

    }
}
