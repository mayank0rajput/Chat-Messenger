package com.example.chatmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitest.ApiClient
import com.example.chatmessenger.adapter.MessageAdapter
import com.example.chatmessenger.adapter.PhysicsAdapter
import com.example.chatmessenger.data.ChatViewModel
import com.example.chatmessenger.data.ItemSpcaingDecoration
import com.example.chatmessenger.databinding.ActivityPhysicsChatBotBinding
import com.example.chatmessenger.model.MessageModel
import com.example.chatmessenger.model.PhysicsMessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhysicsChatBot : AppCompatActivity() {
    private lateinit var binding : ActivityPhysicsChatBotBinding
    private lateinit var mChatViewModel: ChatViewModel      // ViewMode -> Repo -> Dao -> Database
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var adapter: PhysicsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhysicsChatBotBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.backbutton.setOnClickListener {
            finish()
        }
        val messageInputView = binding.message

        // Recycler View
        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter = PhysicsAdapter()
        // item spacing
        val space = resources.getDimension(R.dimen.spacing)
        val itemDecoration = ItemSpcaingDecoration(space)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = mLayoutManager
        binding.recyclerview.addItemDecoration(itemDecoration)
        // View Model Room DB
        mChatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        mChatViewModel.readAllPhysicsChat.observe(this, Observer {chats->
            adapter.setData(chats)
            binding.recyclerview.smoothScrollToPosition(position())
        })
        binding.sendbtn.setOnClickListener {
            val messageInputText = messageInputView.text.toString()
            if (messageInputView.text!!.isBlank()){
                Toast.makeText(this,"Please Enter Text", Toast.LENGTH_SHORT).show()
            }
            else {
                var message = PhysicsMessageModel(messageInputText, true,0)
                mChatViewModel.addPhysicsMessage(message)       // View Model add message to db
//                var msgid = mChatViewModel.getid(message)
                binding.recyclerview.recycledViewPool.clear()
                messageInputView.setText("")
                binding.status.visibility = View.VISIBLE
                val apiClient = ApiClient()
                val conversationId = "mWZdPzKjwaKg"
                val accessToken = "tXAClhNSlq6hb7ZwtXqJSvFFbnyuPVNdkvJfQ1tk"
                lifecycleScope.launch(Dispatchers.IO) {
                    var response = apiClient.generateMessage(messageInputText, conversationId, accessToken)
                    mChatViewModel.addPhysicsMessage(PhysicsMessageModel(response,false,0))    // View Model add reply to db
                    binding.recyclerview.recycledViewPool.clear()
                    binding.status.visibility = View.INVISIBLE
                }
            }
        }
    }
    private fun position() : Int{
        var pos =  adapter.itemCount
        if(pos == 0|| pos == null){
            return 0
        }
        return pos
    }
}