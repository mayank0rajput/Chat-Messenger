package com.example.chatmessenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessenger.R
import com.example.chatmessenger.model.EnglishMessageModel

class EnglishAdapter() : RecyclerView.Adapter<EnglishAdapter.MessageViewHolder>() {
    private var list = emptyList<EnglishMessageModel>()
    inner class MessageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val messageText = view.findViewById<TextView>(R.id.show_message)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var view : View? = null
        var from = LayoutInflater.from(parent.context)
        if(viewType==0){
            view = from.inflate(R.layout.chatrightitem,parent,false)
        }else{
            view = from.inflate(R.layout.chatleftitem,parent,false)
        }
        return MessageViewHolder(view)
    }
    override fun getItemViewType(position: Int): Int {
        var message = list[position]
        return if(message.isUser) 0 else 1
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = list[position]
        holder.messageText.text = message.messageText
    }
    fun setData(chat: List<EnglishMessageModel>){
        this.list = chat
        notifyDataSetChanged()
        notifyItemInserted(chat.size-1)
    }
}