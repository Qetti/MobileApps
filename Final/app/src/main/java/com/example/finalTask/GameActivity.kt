package com.example.finalTask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GameActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        auth = Firebase.auth


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