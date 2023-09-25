package com.example.chatmessenger.data

import androidx.lifecycle.LiveData
import com.example.chatmessenger.model.MessageModel

class ChatRepository(private val chatDao: ChatDao) {
    val readlAllChat : LiveData<List<MessageModel>> = chatDao.readAllChat()

    suspend fun addMessage(chatMessage : MessageModel){
        chatDao.addMessage(chatMessage)
    }
    suspend fun updateMessage(chatMessage : MessageModel){
        chatDao.update(chatMessage)
    }
    fun getid(message: String) : Int {
      val id = chatDao.getid(message)
      return id
    }
}