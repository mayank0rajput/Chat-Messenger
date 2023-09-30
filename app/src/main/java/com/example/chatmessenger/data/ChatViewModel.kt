package com.example.chatmessenger.data

import android.app.Application
import android.graphics.Rect
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessenger.model.EnglishMessageModel
import com.example.chatmessenger.model.MathematicsMessageModel
import com.example.chatmessenger.model.MessageModel
import com.example.chatmessenger.model.PhysicsMessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {
    var readAllChat : LiveData<List<MessageModel>>
    var readAllEnglishChat : LiveData<List<EnglishMessageModel>>
    var readAllMathematicsChat : LiveData<List<MathematicsMessageModel>>
    var readAllPhysicsChat : LiveData<List<PhysicsMessageModel>>
    private val repository : ChatRepository

    init {
        val userDao = ChatDatabase.getDatabase(application).getdao()
        repository = ChatRepository(userDao)
        readAllChat = repository.readlAllChat
        readAllEnglishChat = repository.readlEnglishAllChat
        readAllPhysicsChat = repository.readlPhysicsAllChat
        readAllMathematicsChat = repository.readlMathematicsAllChat
    }

    fun addMessage(chatMessage: MessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMessage(chatMessage)
        }
    }
    fun addEnglishMessage(chatMessage: EnglishMessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnglishMessage(chatMessage)
        }
    }
    fun  addMathematicsMessage(chatMessage: MathematicsMessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMathematicsMessage(chatMessage)
        }
    }
    fun addPhysicsMessage(chatMessage: PhysicsMessageModel){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPhysicsMessage(chatMessage)
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