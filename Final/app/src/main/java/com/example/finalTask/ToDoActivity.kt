package com.example.finalTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class ToDoActivity : AppCompatActivity(), UpdateAndDelete{
    lateinit var database:DatabaseReference
    var toDOList: MutableList<ToDoModel>? = null
    lateinit var adapter: ToDoAdapter
    private var listViewItem : ListView? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        listViewItem = findViewById<ListView>(R.id.item_listView)

        database = FirebaseDatabase.getInstance().reference

        auth = Firebase.auth

        fab.setOnClickListener { view ->
            val alertDialog = AlertDialog.Builder(this)
            val textEditText = EditText(this)
            alertDialog.setMessage("დაამატე შესასრულებელი პუნქტი")
            alertDialog.setTitle("შეიყვანეთ ახალი პუნქტი")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("დამატება"){dialog, i ->
                val todoItemData = ToDoModel.createList()
                todoItemData.itemDataText = textEditText.text.toString()
                todoItemData.done = false

                val newItemData = database.child("todo").push()
                todoItemData.UID = newItemData.key

                newItemData.setValue(todoItemData)

                dialog.dismiss()
                Toast.makeText(this, "პუნქტი შენახულია", Toast.LENGTH_LONG).show()
            }
            alertDialog.show()
        }

        toDOList= mutableListOf<ToDoModel>()
        adapter= ToDoAdapter(this, toDOList!!)
        listViewItem!!.adapter=adapter
        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "არ არის პუნქტები დამატებული", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                toDOList!!.clear()
                addItemToDoList(snapshot)
            }

        })
    }

    private fun addItemToDoList(snapshot: DataSnapshot) {

        val items = snapshot.children.iterator()

        if(items.hasNext()){
            val toDoIndexedValue = items.next()
            val itemsIterator = toDoIndexedValue.children.iterator()

            while (itemsIterator.hasNext()){
                val currentItem = itemsIterator.next()
                val toDoItemData = ToDoModel.createList()
                val map = currentItem.getValue() as HashMap<String, Any>

                toDoItemData.UID = currentItem.key
                toDoItemData.done = map.get("done") as Boolean?
                toDoItemData.itemDataText = map.get("itemDataText") as String?
                toDOList!!.add(toDoItemData)
            }
        }

        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemUID: String, isDone: Boolean) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemUID: String) {
        val itemReference = database.child("todo").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.menuItemLogOut) {
            Log.d("ToDoActivity", "Logout")
            auth.signOut()

            val logOutIntent = Intent(this, LoginActivity::class.java);
            logOutIntent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logOutIntent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}