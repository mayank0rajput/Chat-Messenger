package com.example.chatmessenger.data

import androidx.lifecycle.LiveData
import com.example.chatmessenger.model.EnglishMessageModel
import com.example.chatmessenger.model.MathematicsMessageModel
import com.example.chatmessenger.model.MessageModel
import com.example.chatmessenger.model.PhysicsMessageModel

class ChatRepository(private val chatDao: ChatDao) {
    val readlAllChat : LiveData<List<MessageModel>> = chatDao.readAllChat()
    val readlEnglishAllChat : LiveData<List<EnglishMessageModel>> = chatDao.readAllEnglishChat()
    val readlMathematicsAllChat : LiveData<List<MathematicsMessageModel>> = chatDao.readAllMathematicsChat()
    val readlPhysicsAllChat : LiveData<List<PhysicsMessageModel>> = chatDao.readAllPhysicsChat()

    suspend fun addMessage(chatMessage : MessageModel){
        chatDao.addMessage(chatMessage)
    }

    suspend fun addEnglishMessage(chatMessage : EnglishMessageModel){
        chatDao.addEnglishMessage(chatMessage)
    }
    suspend fun addMathematicsMessage(chatMessage : MathematicsMessageModel){
        chatDao.addMathematicsMessage(chatMessage)
    }
    suspend fun addPhysicsMessage(chatMessage : PhysicsMessageModel){
        chatDao.addPhysicsMessage(chatMessage)
    }
    suspend fun updateMessage(chatMessage : MessageModel){
        chatDao.update(chatMessage)
    }
    fun getid(message: String) : Int {
      val id = chatDao.getid(message)
      return id
    }
}