package com.bottlerunner.room_o_swap

data class Request(var fromHostel:String, var toHostel: MutableList<String>, var id:String?, var name:String)
