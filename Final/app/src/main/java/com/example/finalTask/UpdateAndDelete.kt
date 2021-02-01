package com.example.finalTask

interface  UpdateAndDelete {

    fun modifyItem(itemUID: String, isDone: Boolean)
    fun onItemDelete(itemUID: String)
}