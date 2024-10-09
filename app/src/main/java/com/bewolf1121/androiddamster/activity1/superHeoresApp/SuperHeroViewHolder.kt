package com.bewolf1121.androiddamster.activity1.superHeoresApp

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bewolf1121.androiddamster.databinding.ItemSuperheroBinding
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    // el view holder toma los datos del xml para mostrarlos en un recyclerview
    fun bind(SuperHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = SuperHeroItemResponse.superheroName
        Picasso.get().load(SuperHeroItemResponse.superheroImage.url).into(binding.ivSuperHeroImage)
        binding.root.setOnClickListener { onItemSelected(SuperHeroItemResponse.superheroId) }
    }
}