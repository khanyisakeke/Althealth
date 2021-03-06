package com.example.althealth.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.lifecycle.lifecycleScope
import com.example.althealth.R
import com.example.althealth.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the status bar and make the screen a full screen activity.
        hideStatusBar()

        //Go from the Splash Activity to the next activity
        lifecycleScope.launch {
            delay(1500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish() //Call this when the activity is closed and the app has moved onto the next activity

        }

    }
}