package com.bottlerunner.room_o_swap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(var context: Context, var matchList: MutableList<Request>)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.match_card, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        val tvName: TextView = holder.itemView.findViewById(R.id.tvNameMatchCard)
        val tvFromHostelMatchCard: TextView = holder.itemView.findViewById(R.id.tvFromHostelMatchCard)
        val tvFromHostelRoomNoMatchCard: TextView = holder.itemView.findViewById(R.id.tvFromHostelRoomNoMatchCard)
        val tvToHostelMatchCard: TextView = holder.itemView.findViewById(R.id.tvToHostelMatchCard)
        val tvToHotelRoomNoMatchCard: TextView = holder.itemView.findViewById(R.id.tvToHotelRoomNoMatchCard)
        val tvPhoneNoMatchCard: TextView = holder.itemView.findViewById(R.id.tvPhoneNoMatchCard)

        tvName.text = matchList[position].name
        tvFromHostelMatchCard.text = matchList[position].fromHostel
        tvFromHostelRoomNoMatchCard.text= matchList[position].currRoomNo.toString()
        tvToHostelMatchCard.text = matchList[position].toHostel
        tvToHotelRoomNoMatchCard.text = matchList[position].toHostelRoomNoLower.toString() + "-" + matchList[position].toHostelRoomNoUpper.toString()
        tvPhoneNoMatchCard.text = matchList[position].phoneNo
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
}