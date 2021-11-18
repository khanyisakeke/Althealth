package com.example.althealth.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.althealth.R
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Hide the status bar and make the screen a full screen activity.
        hideStatusBar()

        setupActionBar()

        val tv_login: TextView = findViewById(R.id.tv_login)
        val btn_register: Button = findViewById(R.id.btn_register)

        tv_login.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btn_register.setOnClickListener {
            validateRegisterDetails()
        }

    }

    private fun setupActionBar(){

        val toolbar_register_activity: Toolbar = findViewById(R.id.toolbar_register_activity)

        setSupportActionBar(toolbar_register_activity)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_register_activity.setNavigationOnClickListener { onBackPressed() }

    }
/**
     A function to validate the entries of a new user.
    */

    private fun validateRegisterDetails(): Boolean {

        val et_first_name: EditText = findViewById(R.id.et_first_name)
        val et_last_name: EditText = findViewById(R.id.et_last_name)
        val et_email: EditText = findViewById(R.id.et_email)
        val et_password: EditText = findViewById(R.id.et_password)
        val et_confirm_password: EditText = findViewById(R.id.et_confirm_password)
        val cb_terms_and_condition: CheckBox = findViewById(R.id.cb_terms_and_condition)

        return when {
            TextUtils.isEmpty(et_first_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(et_last_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(et_confirm_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            TextUtils.isEmpty(et_confirm_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            et_password.text.toString().trim { it <= ' ' } != et_confirm_password.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
                }

            !cb_terms_and_condition.isChecked -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
                false
            }
            else -> {
                showErrorSnackBar(resources.getString(R.string.registry_successfull), false)
                true
            }

        }



    }

}