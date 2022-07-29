package com.bottlerunner.room_o_swap.data.model

import android.content.Context
import android.widget.Toast
import com.bottlerunner.room_o_swap.data.UserApna
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class FirestoreClass {

    private val firestore = FirebaseFirestore.getInstance()

    fun registerUser(userInfo: UserApna, context: Context){
        firestore.collection("users").document(getCurrentUserId()).set(userInfo).addOnSuccessListener {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show()
        }
    }

    fun getCurrentUserId(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}