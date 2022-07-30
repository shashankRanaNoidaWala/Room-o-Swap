package com.bottlerunner.room_o_swap

data class Request(var fromHostel:String,var currRoomNo: Int, var toHostelList: MutableList<Pair<String,IntRange>>, var id:String, var name:String)
