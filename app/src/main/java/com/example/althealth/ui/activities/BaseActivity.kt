package com.example.althealth.ui.activities


import android.os.Build

import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.althealth.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class BaseActivity: AppCompatActivity() {

    // A global variable for double back press feature.
    private var doubleBackToExitPressedOnce = false

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