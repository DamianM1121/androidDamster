package com.bewolf1121.androiddamster.activity1.todoapp

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Business : TaskCategory()
    object Personal : TaskCategory()
    object Other : TaskCategory()

}
