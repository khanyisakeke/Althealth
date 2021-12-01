package com.example.althealth.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.althealth.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setupActionBar()

    }

    private fun setupActionBar(){

        val toolbar_forgot_password_activity: Toolbar = findViewById(R.id.toolbar_forgot_password_activity)

        setSupportActionBar(toolbar_forgot_password_activity)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_forgot_password_activity.setNavigationOnClickListener { onBackPressed() }

    }

}