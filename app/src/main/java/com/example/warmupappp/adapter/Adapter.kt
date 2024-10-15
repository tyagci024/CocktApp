package com.example.warmupappp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warmupappp.databinding.ItemLayoutBinding
import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.util.downloadFromURL

class Adapter(private var cocktailList: List<Cocktail>) :
    RecyclerView.Adapter<Adapter.CryptoListViewHolder>() {
    var onItemClickListener: ((Cocktail) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        val cocktail = cocktailList[position]
        holder.bind(cocktail)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(cocktail)
        }
    }//infinite scroll
    //setitemrecycle false

    override fun getItemCount() = cocktailList.size

    class CryptoListViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cocktail: Cocktail) {
                binding.cocktailTitle.text = cocktail.name
                binding.cocktailImage.downloadFromURL(cocktail.image)
            }
        }

    fun updateList(newList: List<Cocktail>) {
        cocktailList = newList
        notifyDataSetChanged()
    }
}