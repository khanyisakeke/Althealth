package com.example.althealth.firestore

import android.util.Log
import com.example.althealth.models.User
import com.example.althealth.ui.activities.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){

        //The "users is a collection name. If the collection is already created then it will not create the same one"
        mFirestore.collection("users")
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

}