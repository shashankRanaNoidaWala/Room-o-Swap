package com.bottlerunner.room_o_swap.data

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bottlerunner.room_o_swap.Database
import com.bottlerunner.room_o_swap.R
import com.bottlerunner.room_o_swap.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyRequestAdapter(var context: Context, var myRequestList: MutableList<Request>, var currUser: UserApna, var currUserTest: UserApna)
    : RecyclerView.Adapter<MyRequestAdapter.MyRequestViewHolder>() {

    inner class MyRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRequestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.my_request_card, parent, false)
        return MyRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyRequestViewHolder, position: Int) {

        val tvToHostelMyRequestCard: TextView = holder.itemView.findViewById(R.id.tvToHostelMyRequestCard)
        val tvToHotelRoomNoMyRequestCard: TextView = holder.itemView.findViewById(R.id.tvToHotelRoomNoMyRequestCard)
        val ivDelete: ImageView =holder.itemView.findViewById(R.id.ivDeleteMyRequestCard)

        tvToHostelMyRequestCard.text = myRequestList[position].toHostel
        tvToHotelRoomNoMyRequestCard.text = myRequestList[position].toHostelRoomNoLower.toString()+ "-" + myRequestList[position].toHostelRoomNoUpper.toString()

        ivDelete.setOnClickListener {

            val userIndex = Database.findIndexById(currUser.id)
            Database.userList[userIndex!!].requestList.removeAt(position)                           //removed from cache
//            currUser.requestList.removeAt(position)                                                 //removed from the list
            Log.d("tag1",currUser.requestList.toString())
            Log.d("tag2",currUserTest.requestList.toString())

            FirebaseFirestore.getInstance().collection("users")
                .document(currUser.id).set(currUser)                                                //pushed to the cloud

//            myRequestList.removeAt(position)                                                        //removed from myRequestList
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,myRequestList.size)
        }
    }

    override fun getItemCount(): Int {
        return myRequestList.size
    }
}