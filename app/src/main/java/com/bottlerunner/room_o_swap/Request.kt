package com.bottlerunner.room_o_swap

data class Request(var fromHostel:String ="",var currRoomNo: Int =0
    , var toHostel: String ="",var toHostelRoomNoLower: Int = 0, var toHostelRoomNoUpper: Int =0
                   , var id:String = "", var name:String = "Ilyich", var phoneNo:String="100")