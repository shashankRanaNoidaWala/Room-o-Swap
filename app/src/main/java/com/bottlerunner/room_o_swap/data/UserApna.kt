package com.bottlerunner.room_o_swap.data

import com.bottlerunner.room_o_swap.Request

data class UserApna(
var id: String = "",
var name: String = "",
var email: String = "",
var password:String ="",
var hostel:String="",
var roomNo:Int=0,
var requestList: MutableList<Request> = mutableListOf<Request>()

)
