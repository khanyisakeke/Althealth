package com.example.althealth.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.example.althealth.R
import com.example.althealth.utils.Constants

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Hide the status bar and make the screen a full screen activity.
       hideStatusBar()

        val tv_register: TextView = findViewById(R.id.tv_register)

        tv_register.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)
    }


}