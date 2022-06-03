package com.tozzz.finalproject.viewmodel

import androidx.lifecycle.ViewModel
import com.tozzz.finalproject.databinding.FragmentDetailBinding
import com.tozzz.finalproject.repo.FoodsDaoRepository

class DetailFragmentViewModel : ViewModel() {
    private var frepo: FoodsDaoRepository

    init {
        frepo = FoodsDaoRepository()
    }

    fun add(
        yemek_id: String,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: String,
        yemek_siparis_adet: String,
        kullanici_adi: String
    ) {
        frepo.addBasket(
            yemek_id,
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
    }

    fun buttonIncDec(detailBinding: FragmentDetailBinding) {
        frepo.btnAddMinus(detailBinding)
    }

}