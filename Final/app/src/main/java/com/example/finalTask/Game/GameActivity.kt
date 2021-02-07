package com.example.finalTask.Game

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.finalTask.LoginActivity
import com.example.finalTask.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        auth = Firebase.auth

        loadBoard()

        button_restart.setOnClickListener {
            board = Board()
            text_view_result.text = ""
            mapBoardToUi()
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

    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }

    var board = Board()


    private fun mapBoardToUi(){

        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when (board.board[i][j]) {
                    Board.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.bidzo)
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.COMPUTER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.misha)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun loadBoard(){

        for(i in boardCells.indices)
        {
            for(j in boardCells.indices)
            {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 250
                    height = 230
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.colorPrimary
                ))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }
    }

    inner class CellClickListener(
        private val i: Int,
        private val j: Int) : View.OnClickListener {

        override fun onClick(p0: View?) {
            if (!board.isGameOver)
            {
                val cell = Cell(i, j)
                board.placeMove(cell, Board.PLAYER)
                board.minimax(0, Board.COMPUTER)
                board.computersMove?.let {
                    board.placeMove(it, Board.COMPUTER)
                }
                mapBoardToUi()
            }
            when
            {
                board.hasMishaWon() -> text_view_result.text = "მიშამ მოიგო"
                board.hasBidzinaWon() -> text_view_result.text = "ბიძინამ მოიგო"
                board.isGameOver -> text_view_result.text = "ნიჩია ძმა"
            }
        }
    }



}