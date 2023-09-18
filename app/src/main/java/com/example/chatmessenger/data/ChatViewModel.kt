package com.example.chatmessenger.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chatmessenger.model.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllChat : LiveData<List<MessageModel>>
    private val repository : ChatRepository

    init {
        val userDao = ChatDatabase.getDatabase(application).getdao()
        repository = ChatRepository(userDao)
        readAllChat = repository.readlAllChat
    }

    fun addMessage(chatMessage: MessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMessage(chatMessage)
        }
    }
}