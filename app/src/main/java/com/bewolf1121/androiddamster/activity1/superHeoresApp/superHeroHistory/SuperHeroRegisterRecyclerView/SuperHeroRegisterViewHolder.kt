package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroRegisterRecyclerView

import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import com.bewolf1121.androiddamster.databinding.SuperheroHistoryRegisterViewBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SuperHeroRegisterViewHolder(private val binding: SuperheroHistoryRegisterViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //Metodo para enlazar los heroes a la vista
    fun bind(heroHistory: SearchHistoryEntity){
        //Asigna el nombre del heroe
        binding.tvHeroNameRegister.text = heroHistory.superheroName

        //Formato de la fecha de consulta
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.tvHeroNameDate.text = dateFormat.format(heroHistory.timestamp)


    }
}