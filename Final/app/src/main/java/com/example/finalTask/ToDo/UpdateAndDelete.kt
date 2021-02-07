package com.example.finalTask.ToDo

interface  UpdateAndDelete {

    fun modifyItem(itemUID: String, isDone: Boolean)
    fun onItemDelete(itemUID: String)
}