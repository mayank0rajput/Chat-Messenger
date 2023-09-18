package com.example.chatmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatmessenger.adapter.MessageAdapter
import com.example.chatmessenger.data.ChatViewModel
import com.example.chatmessenger.databinding.ActivityMainBinding
import com.example.chatmessenger.model.MessageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mChatViewModel: ChatViewModel      // ViewMode -> Repo -> Dao -> Database
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        var auth = Firebase.auth
        verifyUserIsLogged()
        val user = auth.currentUser
        binding.usernametextview.text = user?.email.toString()

        val messageInputView = binding.message

        // Recycler View
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter = MessageAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = mLayoutManager
        // View Model Room DB
        mChatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        mChatViewModel.readAllChat.observe(this, Observer {chats->
            adapter.setData(chats)
        })
        binding.recyclerview.smoothScrollToPosition(adapter.itemCount+2)
        binding.recyclerview.smoothScrollToPosition(position())
        binding.sendbtn.setOnClickListener {
            val messageInputText = messageInputView.text.toString()
            if (messageInputView.text!!.isBlank()){
                Toast.makeText(this,"Please Enter Text",Toast.LENGTH_SHORT).show()
            }
            else {
                var message = MessageModel(messageInputText, true,0)
                mChatViewModel.addMessage(message)       // View Model add message to db
                binding.recyclerview.recycledViewPool.clear()
//                binding.recyclerview.smoothScrollToPosition()
            messageInputView.setText(" ")
                var replyText = messageInputText+" " + messageInputText
                var reply = MessageModel(replyText,false,0)
                mChatViewModel.addMessage(reply)    // View Model add reply to db
                Toast.makeText(this,"Position ${adapter.itemCount}",Toast.LENGTH_SHORT).show()
                binding.recyclerview.recycledViewPool.clear()
                binding.recyclerview.smoothScrollToPosition(adapter.itemCount+2)
            }
        }
    }
    private fun verifyUserIsLogged(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid==null){
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    private fun position() : Int{
        var pos =  adapter.itemCount
        if(pos == 0|| pos == null){
            return 0
        }
        return pos
    }
    private fun insertIntoData(){}
}