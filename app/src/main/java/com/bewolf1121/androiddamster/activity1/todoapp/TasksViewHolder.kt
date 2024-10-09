package com.bewolf1121.androiddamster.activity1.todoapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTasks: TextView = view.findViewById(R.id.tvTasks)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {

        if (task.isSelected){
            tvTasks.paintFlags= tvTasks.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTasks.paintFlags= tvTasks.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        tvTasks.text = task.name
        //cuando el checkBox esta seleccionado tambien lo esta el item del task
        cbTask.isChecked = task.isSelected

        val color = when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }
        //doy un color al boton cbtask = a un valor dentro de la lista de los colores recorriendo
        //el contexto de cbntask y el color como variable separada en el when
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )



    }
}
