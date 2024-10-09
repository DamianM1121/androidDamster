package com.bewolf1121.androiddamster.activity1.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.R
import com.bewolf1121.androiddamster.activity1.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

private val tasks = mutableListOf(
    Task("Prueba Business", Business),
    Task("Prueba Personal", Personal),
    Task("Prueba Other", Other)
)

//vistas de categorias
private lateinit var rvCategories: RecyclerView
private lateinit var categoriesAdapter: CategoriesAdapter

//tasks
private lateinit var rvTasks: RecyclerView
private lateinit var tasksAdapter: TasksAdapter

//boton para agregar tareas
private lateinit var fabAddTask: FloatingActionButton
//cardView de Task Category




private val categories = listOf(
    Personal,
    Business,
    Other
)

class TodoappActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoapp)
        initComponents()
        initUi()
        initListeners()

    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        //se recorren los ID de la vista
        val bnAddTask: Button = dialog.findViewById(R.id.bnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        //al presionar el boton la ID me devolvera radiobutton seleccionado
        //del radio group busca por ID el selected ID
        bnAddTask.setOnClickListener {
            //el currentTask muestra la siguiente tarea como string el tx de etTask.text
            val currentTask = etTask.text.toString()
            //si no esta vacio devuelve y agrega el valor
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_category_business) -> Business
                    getString(R.string.todo_category_personal) -> Personal
                    else -> Other
                }
                //devuelve el valor en string del texto que contiene etTask
                //añade una nueva tarea
                tasks.add(Task(currentTask, currentCategory))
                //avisa al adapter que se cargaron nuevos items
                updateTask()
                //esconde el dialogo
                dialog.hide()
            }
        }


        dialog.show()
    }

    private fun initUi() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> updateCategories(position) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks,){position->onItemSelected(position)}
        //como es vertical el recycler view no es necesario escribir los demas parametros
        rvTasks.layoutManager = LinearLayoutManager(this)
        //al recycler view rvTasks le paso el adapter de tasksAdapter
        rvTasks.adapter = tasksAdapter

    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)


    }
    //Selecciona o deselecciona un item
    private fun onItemSelected(position: Int){
        //si el item tasks esta seleccionado entonces se deseleccionara o viceversa
        tasks[position].isSelected = !tasks[position].isSelected
        updateTask()

    }
    //recarga las categorias
    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }
    //recarga las tareas
    //Recorre el taskCategory y filtra solo aquellas tareas que esten seleccionadas
    private fun updateTask() {
        val selectedCategory: List<TaskCategory> = categories.filter { it.isSelected }
        val newTask = tasks.filter{selectedCategory.contains(it.category)}
        tasksAdapter.tasks = newTask
        tasksAdapter.notifyDataSetChanged()

    }
}

