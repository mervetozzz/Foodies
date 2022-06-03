package com.tozzz.finalproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tozzz.finalproject.databinding.CardFoodsBinding
import com.tozzz.finalproject.entity.Foods
import com.tozzz.finalproject.fragment.FoodsFragmentDirections
import com.tozzz.finalproject.viewmodel.FoodsFragmentViewModel

class FoodAdapter(var mContext: Context,var foodsList: List<Foods>, viewModel:FoodsFragmentViewModel) :
    RecyclerView.Adapter<FoodAdapter.FoodsCardDesignHolder>() {

    inner class FoodsCardDesignHolder(adapterBinding: CardFoodsBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {
        var binding: CardFoodsBinding

        init {
            this.binding = adapterBinding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsCardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardFoodsBinding.inflate(layoutInflater, parent, false)
        return FoodsCardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsCardDesignHolder, position: Int) {
        val food = foodsList.get(position)
        val h = holder.binding
        h.foodsObject = food
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
        Picasso.get().load(url).into(h.imageView)
        h.cardFood.setOnClickListener {
            val trans = FoodsFragmentDirections.toDetail(selected = food)
            Navigation.findNavController(it).navigate(trans)
        }
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}