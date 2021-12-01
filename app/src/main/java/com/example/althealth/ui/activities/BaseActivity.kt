package com.example.althealth.ui.activities


import android.app.Dialog
import android.os.Build

import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.althealth.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
* A base activity class is used to define the functions and members which we will use in all the activities.
* It inherits the AppCompatActivity class so in other activity class we will replace the AppCompatActivity with BaseActivity.
*/

open class BaseActivity: AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

    // A global variable for double back press feature.
    private var doubleBackToExitPressedOnce = false

    /**
     * A function to show the success and error messages in snack bar component.
     */

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage){
            snackBarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarError))
        } else {
            snackBarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarSuccess))
        }
        snackBar.show()
    }

    fun showProgressDialog(text: String){
        mProgressDialog = Dialog(this)

        mProgressDialog.setContentView(R.layout.dialog_progress)

        val tv_progress_text: TextView = mProgressDialog.findViewById(R.id.tv_progress_text) as TextView

        tv_progress_text.text = text

        mProgressDialog.setCancelable(false)

        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()

    }

    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    // This function is used to hide the status bar and make the screen a full screen activity.

    fun hideStatusBar(){

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }

    /**
     * A function to implement the double back press feature to exit the app.
     */
    fun doubleBackToExit() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true

        Toast.makeText(
            this, resources.getString(R.string.please_click_back_again_to_exit),
            Toast.LENGTH_SHORT
        ).show()

          lifecycleScope.launch {
            delay(1500)
            doubleBackToExitPressedOnce = false
        }

    }



}