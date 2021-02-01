package com.example.finalTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoButton = findViewById<Button>(R.id.toDoButton)
        val gameButton = findViewById<Button>(R.id.gameButton)

        auth = Firebase.auth


        toDoButton.setOnClickListener{
            startActivity(Intent(this, ToDoActivity::class.java))
        }
        gameButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.menuItemLogOut) {
            Log.d("GameActivity", "Logout")
            auth.signOut()

            val logOutIntent = Intent(this, LoginActivity::class.java);
            logOutIntent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logOutIntent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }



}