package com.example.althealth.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.althealth.R
import com.example.althealth.firestore.FirestoreClass
import com.example.althealth.models.User
import com.example.althealth.utils.Constants
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Hide the status bar and make the screen a full screen activity.
       hideStatusBar()

        val tv_forgot_password: TextView = findViewById(R.id.tv_forgot_password)
        val tv_register: TextView = findViewById(R.id.tv_register)
        val btn_login: Button = findViewById(R.id.btn_login)

        //Click event assigned to Forgot Password textview
        tv_forgot_password.setOnClickListener(this)
        //Click event assigned to Login button.
        btn_login.setOnClickListener(this)
        //Click event assigned to Register textview
        tv_register.setOnClickListener(this)

    }

    fun userLoggedInSuccess(user: User){

        // Hide the progress dialog.
        hideProgressDialog()

        //Print the user details in the log as of now
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        //Redirect the user to Main Screen after log in.
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    //In the Login screen the clickable components are the Login Button, the ForgotPassword textview and the Register textview.
    override fun onClick(v: View?) {

        if (v != null){
            when (v.id){
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login -> {

                    loginRegisteredUser()

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

                true
            }
        }
    }

private fun loginRegisteredUser(){

    val et_email: EditText = findViewById(R.id.et_email)
    val et_password: EditText = findViewById(R.id.et_password)

    if (validateLoginDetails()) {

        //Show the progress dialog
        showProgressDialog(resources.getString(R.string.please_wait))

        //Get the text from ediText boxes and trim the space
        val email = et_email.text.toString().trim { it <= ' ' }
        val password = et_password.text.toString().trim { it <= ' ' }

        //Log-In using FirebaseAuth
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    FirestoreClass().getUserDetails(this@LoginActivity)

                } else {
                    hideProgressDialog()
                    showErrorSnackBar(task.exception!!.message.toString(), true)
                }
            }

    }

}

}