package com.example.chatmessenger.data

import android.app.Application
import android.graphics.Rect
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessenger.model.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    var readAllChat : LiveData<List<MessageModel>>
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
    fun updateMessage(chatMessage : MessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMessage(chatMessage)
        }
    }
    fun getid(message: String):Int{
        return repository.getid(message)
    }
}
class ItemSpcaingDecoration(private val spacing: Float): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        outRect.left = spacing
//        outRect.right = spacing
//        outRect.top = spacing
//        outRect.bottom = spacing
        outRect.top = spacing.toInt()
    }
}