package com.example.finalTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoButton = findViewById<Button>(R.id.toDoButton)
        val gameButton = findViewById<Button>(R.id.gameButton)


        toDoButton.setOnClickListener{
            startActivity(Intent(this, ToDoActivity::class.java))
        }
        gameButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

    }



}