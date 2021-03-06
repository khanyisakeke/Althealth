package com.example.althealth.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.althealth.R
import com.example.althealth.models.Reference
import com.example.althealth.models.User
import com.example.althealth.ui.activities.LoginActivity
import com.example.althealth.ui.activities.RegisterActivity
import com.example.althealth.ui.activities.UserProfileActivity
import com.example.althealth.utils.Constants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){

        //The "users is a collection name. If the collection is already created then it will not create the same one"
        mFirestore.collection(Constants.USERS)
        //Document ID for users fields. Here the document is the User ID.
            .document(userInfo.id)
        //Here the userInfo are Field and the SetOption is set to merge. Just in case we want to merge later.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                //Here call a function of th base activity for transferring the result to it.
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while registering the user",
                    e
                )
            }
    }

    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun getUserDetails(activity: Activity){
        //Here we pass the collection name from which we want the data.
        mFirestore.collection(Constants.USERS)
        //The document id to get the Fields of user
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(activity.javaClass.simpleName, document.toString())

                // Here we have received the document snapshot which is converted into the User Data model object.
                val user = document.toObject(User::class.java)!!

                val sharedPreferences = activity.getSharedPreferences(
                    Constants.Althealth_PREFERENCES,
                    Context.MODE_PRIVATE
                )
                    //Key: Value logged_in_username: Khanyisa Keke
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(Constants.LOGGED_IN_USERNAME,
                "${user.firstName} ${user.lastName}"
                )

                editor.apply()

                when (activity){
                    is LoginActivity -> {
                        // Call a function of base activity for transferring the result to it
                        activity.userLoggedInSuccess(user)
                    }
                }
            }
    }

    fun getReferenceList(activity: UserProfileActivity) {

        val referencesRef = mFirestore.collection(Constants.REFERENCE)
        // Here we have created a new instance for References ArrayList.
        val references: MutableList<String?> = ArrayList()

        referencesRef.get().addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
            if (task.isSuccessful) {

                // A for loop as per the task.result to convert them into the References ArrayList.

                for (document in task.result!!) {

                    val reference = document.getString("c_reference")

                    references.add(reference)
                }

                activity.successReferenceList(references)

            } else {

                // Hide the progress dialog if there is any error.
                activity.hideProgressDialog()

                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting the list of references."
                )

            }
        })

    }

}