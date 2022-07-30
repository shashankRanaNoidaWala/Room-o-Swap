package com.bottlerunner.room_o_swap

import com.bottlerunner.room_o_swap.data.UserApna

object Database {

    var requestList: MutableList<Request> =
        mutableListOf()

    var userList: MutableList<UserApna> = mutableListOf( UserApna("QRl6gkWBrDhgjKRyONr91Yh9pqL2","Vladimir Putin"
        ,"vladimirputin@cykablyat.ussr","cykablyat","AH-4",123,mutableListOf()))

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