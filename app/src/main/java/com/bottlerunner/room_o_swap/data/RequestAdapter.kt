package com.bottlerunner.room_o_swap.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bottlerunner.room_o_swap.Database
import com.bottlerunner.room_o_swap.R
import com.bottlerunner.room_o_swap.Request

class RequestAdapter(var context: Context, var history: MutableList<Request>)
    : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    inner class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.request_card, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {

        val tv_from_card: TextView = holder.itemView.findViewById(R.id.tv_from_card)
        val tv_to_card: TextView = holder.itemView.findViewById(R.id.tv_to_card)
        val tvNameRequestCard: TextView = holder.itemView.findViewById(R.id.tvNameRequestCard)

        tvNameRequestCard.text = Database.requestList[position].name
        tv_from_card.text = Database.requestList[position].fromHostel
        tv_to_card.text = Database.requestList[position].toHostel
   }

    override fun getItemCount(): Int {
        return Database.requestList.size
    }
}