package com.bottlerunner.room_o_swap

import android.content.Context
import android.widget.Toast
import com.bottlerunner.room_o_swap.data.UserApna
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObjects

object Database {


    var requestList: MutableList<Request> =
        mutableListOf()

    var userList: MutableList<UserApna> = mutableListOf()

    var matchList: MutableList<Request> = mutableListOf()


    fun findUserById(id: String):UserApna?{

        for(i in userList){
            if(i.id == id){
                return i
            }
        }
        return null
    }

    fun findIndexById(id: String):Int?{
        for( i in 0..(userList.size-1) ){
            if(userList[i].id ==id){
                return i
            }
        }
        return null
    }

    fun makeMatchList(user: UserApna):MutableList<Request>{

        var matchList: MutableList<Request> = mutableListOf()
        for(i in Database.userList){
            if(matchUsers(user, i) !=null){
                matchList.add(matchUsers(user,i)!!)
            }
        }
        return matchList
    }

    fun matchUsers(userMe: UserApna, user2: UserApna):Request?{

        var foundInUserMe=false
        for(i in userMe.requestList){
            if(i.toHostel == user2.hostel && (i.toHostelRoomNoLower <= user2.roomNo
                        && user2.roomNo <= i.toHostelRoomNoUpper)){
                foundInUserMe=true
                break
                }
        }

        if(foundInUserMe){
            for(i in user2.requestList){
                if(i.toHostel == userMe.hostel && (i.toHostelRoomNoLower <= userMe.roomNo
                            && userMe.roomNo <= i.toHostelRoomNoUpper)){
                    return i;
                }
            }
        }
        return null
    }
}