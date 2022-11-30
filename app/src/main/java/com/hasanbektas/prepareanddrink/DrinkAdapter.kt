package com.hasanbektas.prepareanddrink

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.DrinkFragmentDirections
import com.hasanbektas.PrepareFragment
import com.hasanbektas.prepareanddrink.databinding.RecyclerRowBinding
import java.util.*
import kotlin.collections.ArrayList

class DrinkAdapter (val drinkList : ArrayList<DrinkData>): RecyclerView.Adapter<DrinkAdapter.ArtHolder>() {

    private val colors: Array<String> = arrayOf("#484848","#545454","#5E5E5E")

    class ArtHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {

        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {

        holder.binding.recyclerViewTextView.text = drinkList.get(position).strDrink
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position%3]))

        holder.itemView.setOnClickListener {

            val action = DrinkFragmentDirections.actionDrinkFragmentToPrepareFragment(arrayOf("$drinkList"),drinkList.get(position))
           Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return drinkList.size
    }

}

