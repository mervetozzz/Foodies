package com.tozzz.finalproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tozzz.finalproject.entity.Basket
import com.tozzz.finalproject.repo.FoodsDaoRepository

class BasketFragmentViewModel : ViewModel() {
    var basketList : MutableLiveData<List<Basket>>
    private var frepo = FoodsDaoRepository()
    val user = FirebaseAuth.getInstance().currentUser?.email.toString()

    init {
        loadBasket()
        basketList = frepo.getBasket()
    }

    fun delete(sepet_yemek_id: Int,kullanici_adi: String){
        frepo.delete(sepet_yemek_id,kullanici_adi)
    }

    fun loadBasket(){
        frepo.allOrder(user)
    }
}