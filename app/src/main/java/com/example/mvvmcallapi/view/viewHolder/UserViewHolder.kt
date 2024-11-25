package com.example.mvvmcallapi.view.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcallapi.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
}