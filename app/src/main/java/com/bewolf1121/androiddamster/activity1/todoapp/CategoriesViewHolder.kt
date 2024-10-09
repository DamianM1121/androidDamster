package com.bewolf1121.androiddamster.activity1.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txCategoryName: TextView = view.findViewById(R.id.txCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)


    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        itemView.setOnClickListener{onItemSelected(layoutPosition)}

        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }
        txCategoryName.text = "Ejemplo"
        //añadele el color
        viewContainer.setCardBackgroundColor(
            ContextCompat.getColor(viewContainer.context, color)
        )

        when (taskCategory) {
            TaskCategory.Business -> {
                txCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                )
            }

            TaskCategory.Other -> {
                txCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }

            TaskCategory.Personal -> {
                txCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
        }
    }
}