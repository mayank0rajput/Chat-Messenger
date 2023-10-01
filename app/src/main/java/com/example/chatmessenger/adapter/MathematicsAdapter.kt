package com.example.chatmessenger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessenger.R
import com.example.chatmessenger.model.EnglishMessageModel
import com.example.chatmessenger.model.MathematicsMessageModel

class MathematicsAdapter() : RecyclerView.Adapter<MathematicsAdapter.MessageViewHolder>() {
    private var list = emptyList<MathematicsMessageModel>()
    inner class MessageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val messageText = view.findViewById<TextView>(R.id.show_message)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathematicsAdapter.MessageViewHolder {
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
    override fun onBindViewHolder(holder: MathematicsAdapter.MessageViewHolder, position: Int) {
        val message = list[position]
        holder.messageText.text = message.messageText
    }
    fun setData(chat: List<MathematicsMessageModel>){
        this.list = chat
        notifyDataSetChanged()
        notifyItemInserted(chat.size-1)
    }
}