package com.bewolf1121.androiddamster.activity1.todoapp

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false) {
}