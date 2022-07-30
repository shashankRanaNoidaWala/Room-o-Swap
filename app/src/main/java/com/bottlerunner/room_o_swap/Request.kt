package com.bottlerunner.room_o_swap

data class Request(var fromHostel:String ="",var currRoomNo: Int =0
, var toHostel: Pair<String,Pair<Int,Int>> = Pair("CH-5",Pair(0,400)), var id:String = "", var name:String = "Ilyich")