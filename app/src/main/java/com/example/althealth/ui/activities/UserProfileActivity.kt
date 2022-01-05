package com.example.althealth.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.althealth.R
import com.example.althealth.firestore.FirestoreClass
import com.example.althealth.models.User
import com.example.althealth.utils.Constants


class UserProfileActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val et_first_name: TextView = findViewById(R.id.et_first_name)
        val et_last_name: TextView = findViewById(R.id.et_last_name)
        val et_email: TextView = findViewById(R.id.et_email)
        val iv_user_photo: ImageView = findViewById(R.id.iv_user_photo)

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

        iv_user_photo.setOnClickListener(this@UserProfileActivity)

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

    override fun onClick(v: View?) {
        if (v !=null){
            when (v.id){

                R.id.iv_user_photo -> {
                    //Here we will check if the permission is already allowed or we need to request for it.
                    //First of all we will check the READ_EXTERNAL_STORAGE permission and if it is not allowed
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    == PackageManager.PERMISSION_GRANTED
                    ){
                        showErrorSnackBar("You already have the storage permission.", false)
                    } else {

                        /*Requests permissions to be granted to this application. These permissions
                        must be requested in your manifest, they should not be granted to your app,
                        and they should have protection level*/

                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE){
            //If permission is granted
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showErrorSnackBar("The storage permission is granted", false)
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(
                    this,
                    resources.getString(R.string.read_storage_permission_denied),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}

