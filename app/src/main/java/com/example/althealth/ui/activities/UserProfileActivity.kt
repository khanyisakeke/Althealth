package com.example.althealth.ui.activities

import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.althealth.R
import com.example.althealth.firestore.FirestoreClass
import com.example.althealth.models.User
import com.example.althealth.utils.Constants


class UserProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val et_first_name: TextView = findViewById(R.id.et_first_name)
        val et_last_name: TextView = findViewById(R.id.et_last_name)
        val et_email: TextView = findViewById(R.id.et_email)

        var userDetails: User = User()
            if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
                //Get the user details from intent as a ParcelableExtra.
                userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
            }

        et_first_name.isEnabled = false
        et_first_name.setText(userDetails.firstName)

        et_last_name.isEnabled = false
        et_last_name.setText(userDetails.lastName)

        et_email.isEnabled = false
        et_email.setText(userDetails.email)

    }

    override fun onResume() {
        super.onResume()

        getReferenceList()

    }

    // a function to pass the UserProfile Activity to the Firestore class where it will populate the references ArrayList

   private fun getReferenceList(){

        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getReferenceList(this@UserProfileActivity)

    }

    // A function to populate the spinner with the references from the references table in Cloud Firestore
    fun successReferenceList(referenceList:MutableList<String?>){

        hideProgressDialog()

        val spn_reference: Spinner = findViewById(R.id.spn_reference)

        if (referenceList.size > 0){


            val adapter =
                ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, referenceList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spn_reference.adapter = adapter

            adapter.notifyDataSetChanged()

        }

    }

    }

