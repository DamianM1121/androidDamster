package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroRegisterRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import com.bewolf1121.androiddamster.databinding.SuperheroHistoryRegisterViewBinding

// El adaptador recibe una lista de entidades SearchHistoryEntity
class SuperHeroRegisterAdapter(
    private var heroList: MutableList<SearchHistoryEntity>
) : RecyclerView.Adapter<SuperHeroRegisterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroRegisterViewHolder {
        // Inflamos el layout usando ViewBinding
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SuperheroHistoryRegisterViewBinding.inflate(layoutInflater, parent, false)
        return SuperHeroRegisterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperHeroRegisterViewHolder, position: Int) {
        // Obtenemos el héroe de la lista
        val hero = heroList[position]
        // Vinculamos el héroe al ViewHolder
        holder.bind(hero)
    }

    override fun getItemCount(): Int {
        // Devolvemos el tamaño de la lista
        return heroList.size
    }

    //Metodo para actualizar la lista de heroes de forma dinamica
    fun updateHeroList(newList: List<SearchHistoryEntity>) {
        heroList.clear()
        heroList.addAll(newList)
        notifyDataSetChanged()

    }
}
