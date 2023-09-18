package com.example.chatmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
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

    var MessagesList = ArrayList<MessageModel>()
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
        adapter = MessageAdapter(MessagesList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = mLayoutManager
        // View Model Room DB
        mChatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        binding.sendbtn.setOnClickListener {
            val messageInputText = messageInputView.text.toString()
            if (messageInputView.text!!.isBlank()){
//                return@setOnClickListener
//                Toast.makeText(this,"Please Enter Text",Toast.LENGTH_SHORT).show()
            }
            else {
                var message = MessageModel(messageInputText, true,0)
                MessagesList.add(message)
                mChatViewModel.addMessage(message)       // View Model add message to db
                adapter.notifyItemInserted(MessagesList.size - 1)
                binding.recyclerview.recycledViewPool.clear()
                binding.recyclerview.smoothScrollToPosition(MessagesList.size-1)
            messageInputView.setText(" ")
                var replyText = messageInputText+" " + messageInputText
                var reply = MessageModel(replyText,false,0)
                MessagesList.add(reply)
                mChatViewModel.addMessage(reply)    // View Model add reply to db
                adapter.notifyItemInserted(MessagesList.size - 1)
                binding.recyclerview.recycledViewPool.clear()
                binding.recyclerview.smoothScrollToPosition(MessagesList.size-1)
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
    private fun insertIntoData(){}
}