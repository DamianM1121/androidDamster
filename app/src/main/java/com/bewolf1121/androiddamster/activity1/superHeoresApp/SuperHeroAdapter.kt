package com.bewolf1121.androiddamster.activity1.superHeoresApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.R

class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superHeroList: List<SuperHeroItemResponse>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )


    }

    override fun getItemCount() = superHeroList.size

    //primero cargo el item, luego defino la posicion para el orden
    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {

        viewholder.bind(superHeroList[position], onItemSelected)
    }

    // Nueva función para obtener un héroe por su ID
    fun getHeroById(id: String): SuperHeroItemResponse? {
        return superHeroList.find { it.superheroId == id }  // Busca el héroe por su ID dentro de la lista
    }

}
