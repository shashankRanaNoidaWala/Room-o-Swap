package com.bottlerunner.room_o_swap

import android.content.Context
import android.widget.Toast
import com.bottlerunner.room_o_swap.data.UserApna
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

object Database {

    var requestList: MutableList<Request> =
        mutableListOf()

    var userList: MutableList<UserApna> = mutableListOf()

    fun findUserById(id: String):UserApna?{

        for(i in userList){
            if(i.id == id){
                return i
            }
        }
        return null
    }

    fun findIndexById(id: String):Int?{
        for( i in 1..userList.size ){
            if(userList[i].id ==id){
                return i
            }
        }
        return null
    }
}