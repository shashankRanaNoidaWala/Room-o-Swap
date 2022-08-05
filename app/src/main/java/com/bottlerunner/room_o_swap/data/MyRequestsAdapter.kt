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

class MyRequestAdapter(var context: Context, var myRequestList: MutableList<Request>                //remember that as Database is an object, all the stuff derived from it
    , var currUser: UserApna, var currUserTest: UserApna)                                           //is pass by refernce. There fore if something is changed anywhere on thing which had some bussiness
    : RecyclerView.Adapter<MyRequestAdapter.MyRequestViewHolder>() {                                //with database, it's value in database will change as well. Continues at bottom

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
            currUser.requestList.removeAt(position)                                                 //removed from the list
            FirebaseFirestore.getInstance().collection("users")
                .document(currUser.id).set(currUser)                                                //pushed to the cloud
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,myRequestList.size)

            //see footnote
        }
    }

    override fun getItemCount(): Int {
        return myRequestList.size
    }
}

//If we had user1= Database.findUserById(Firestore.currentUser.uid)
//and user2 = Database.findUserById(--""--)
//and do user1.requestList.removeAt(0)
//the same thing will be removed from user2 too.