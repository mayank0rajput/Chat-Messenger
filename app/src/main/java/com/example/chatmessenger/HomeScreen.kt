package com.example.chatmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.chatmessenger.databinding.ActivityHomeScreenBinding
import com.google.firebase.auth.FirebaseAuth

class HomeScreen : AppCompatActivity() {
    lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        verifyUserIsLogged()

        val cardMaths = binding.cardmaths
        val cardEndlish = binding.cardenglish
        val cardcomputerscience = binding.cardcs
        val cardPhysics = binding.cardphysics
        cardMaths.setOnClickListener {
            openMathsChat()
        }
        cardEndlish.setOnClickListener {
            openEnglishChat()
        }
        cardcomputerscience.setOnClickListener {
            openComputerScienceChat()
        }
        cardPhysics.setOnClickListener {
            openPhysicsChat()
        }
        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            verifyUserIsLogged()
        }
    }
    private fun openMathsChat(){
        val intent = Intent(this,MathematicsChatBot::class.java)
        Toast.makeText(this,"Opening Mathematics Chat-Bot",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    private fun openEnglishChat(){
        val intent = Intent(this,EnglishChatBot::class.java)
        Toast.makeText(this,"Opening English Chat-Bot",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    private fun openComputerScienceChat(){
        val intent = Intent(this,MainActivity::class.java)
        Toast.makeText(this,"Opening Computer Science Chat-Bot",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    private fun openPhysicsChat(){
        val intent = Intent(this,PhysicsChatBot::class.java)
        Toast.makeText(this,"Opening Physics Chat-Bot",Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
    private fun verifyUserIsLogged(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid==null){
            val intent = Intent(this,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}