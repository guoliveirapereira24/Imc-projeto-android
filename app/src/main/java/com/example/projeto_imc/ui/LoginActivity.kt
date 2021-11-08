package com.example.projeto_imc.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.projeto_imc.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvNovaConta = findViewById<TextView>(R.id.tv_criarConta)

        tvNovaConta.setOnClickListener{
            val NovaConta = Intent(this, ProfileActivity::class.java)
        }
    }
}