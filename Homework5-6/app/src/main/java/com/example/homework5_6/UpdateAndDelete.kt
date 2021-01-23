package com.example.homework5_6

interface  UpdateAndDelete {

    fun modifyItem(itemUID: String, isDone: Boolean)
    fun onItemDelete(itemUID: String)
}