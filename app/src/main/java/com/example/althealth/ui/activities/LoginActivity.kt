package com.example.althealth.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
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

    //In the Login screen the clickable components are the Login Button, the ForgotPassword textview and the Register textview.
    override fun onClick(v: View?) {

        if (v != null){
            when (v.id){
                R.id.tv_forgot_password -> {

                }
                R.id.btn_login -> {
                    validateLoginDetails()
                }
                R.id.tv_register -> {
                    //Launch the register screen when the user clicks on the text
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)

                }
            }
        }

    }

    private fun validateLoginDetails(): Boolean {

        val et_email: EditText = findViewById(R.id.et_email)
        val et_password: EditText = findViewById(R.id.et_password)

        return when {
            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid", false)
                true
            }
        }
    }

}