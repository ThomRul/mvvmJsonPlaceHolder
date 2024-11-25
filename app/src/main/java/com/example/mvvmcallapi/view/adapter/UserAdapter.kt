package com.example.mvvmcallapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcallapi.R
import com.example.mvvmcallapi.model.User
import com.example.mvvmcallapi.view.viewHolder.UserViewHolder

class UserAdapter(val users: List<User>) : RecyclerView.Adapter<UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = this.users[position]
        holder.nameTextView.text = data.name
        holder.emailTextView.text = data.email
    }

    override fun getItemCount(): Int {
        return users.size
    }
}