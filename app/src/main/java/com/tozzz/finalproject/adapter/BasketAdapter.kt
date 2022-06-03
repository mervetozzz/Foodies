package com.tozzz.finalproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.tozzz.finalproject.databinding.CardBasketBinding
import com.tozzz.finalproject.entity.Basket
import com.tozzz.finalproject.fragment.FoodsFragmentDirections
import com.tozzz.finalproject.viewmodel.BasketFragmentViewModel

class BasketAdapter(var mContext:Context,
                    var basketList: List<Basket>,
                    var viewModel : BasketFragmentViewModel)
    :RecyclerView.Adapter<BasketAdapter.BasketCardHolder>() {
    inner class BasketCardHolder(binding:CardBasketBinding):RecyclerView.ViewHolder(binding.root){
        var binding:CardBasketBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardBasketBinding.inflate(layoutInflater,parent,false)
        return BasketCardHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketCardHolder, position: Int) {
        val order = basketList.get(position)
        val o = holder.binding
        o.foodBasketObject = order

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${order.yemek_resim_adi}"
        Picasso.get().load(url).into(o.foodImage)

        o.delete.setOnClickListener {
            Snackbar.make(it,"${order.yemek_adi} Silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    viewModel.delete(order.sepet_yemek_id,order.kullanici_adi)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}