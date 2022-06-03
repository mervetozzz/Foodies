package com.tozzz.finalproject.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.tozzz.finalproject.databinding.FragmentDetailBinding
import com.tozzz.finalproject.entity.*
import com.tozzz.finalproject.retrofit.ApiUtils
import com.tozzz.finalproject.retrofit.FoodsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class FoodsDaoRepository {
    private var fdao: FoodsDao
    var foodList: MutableLiveData<List<Foods>>
    var basketFoodList: MutableLiveData<List<Basket>>
    val user = FirebaseAuth.getInstance().currentUser?.email.toString()


    init {
        fdao = ApiUtils.getFoodsDao()
        foodList = MutableLiveData()
        basketFoodList = MutableLiveData()
    }

    fun getFoods(): MutableLiveData<List<Foods>> {
        return foodList
    }

    fun allFoods() {
        fdao.allFood().enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body()?.yemekler
                Log.i("FOOD LIST RESPONSE", response.body().toString())
                foodList.value = list
            }

            override fun onFailure(call: Call<FoodsAnswer>, t: Throwable) {}
        })
    }

    fun getBasket(): MutableLiveData<List<Basket>> {
        return basketFoodList
    }

    fun allOrder(kullanici_adi: String) {
        fdao.basket(user).enqueue(object : Callback<BasketFoodAnswer> {
            override fun onResponse(
                call: Call<BasketFoodAnswer>,
                response: Response<BasketFoodAnswer>
            ) {
                val list = response.body()?.sepet_yemekler
                basketFoodList.value = list
            }

            override fun onFailure(call: Call<BasketFoodAnswer>, t: Throwable) {}
        })
    }

    fun addBasket(
        sepet_yemek_id: String,
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: String,
        yemek_siparis_adet: String,
        kullanici_adi: String
    ) {
        fdao.addBasket(
            sepet_yemek_id,
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
            .enqueue(object : Callback<CRUDFoodAnsw> {
                override fun onResponse(
                    call: Call<CRUDFoodAnsw>,
                    response: Response<CRUDFoodAnsw>
                ) {
                    try {
                        if (basketFoodList.value != null && basketFoodList.value!!.isNotEmpty()) {
                            allOrder(kullanici_adi)
                        }
                        allOrder(kullanici_adi)
                    } catch (e: Exception) {
                    }
                }

                override fun onFailure(call: Call<CRUDFoodAnsw>, t: Throwable) {}
            })
    }

    fun delete(sepet_yemek_id: Int, kullanici_adi: String) {
        fdao.deleteOrder(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDFoodAnsw> {
            override fun onResponse(call: Call<CRUDFoodAnsw>, response: Response<CRUDFoodAnsw>) {
                try {
                    if (basketFoodList.value!!.size == 1) {
                        basketFoodList.value = emptyList()
                    }
                    allOrder(kullanici_adi)

                } catch (e: Exception) {
                }
            }

            override fun onFailure(call: Call<CRUDFoodAnsw>, t: Throwable) {}
        })
    }

    fun orderPiece(piece: Int, detailBinding: FragmentDetailBinding) {
        detailBinding.foodNum.text = "$piece"
    }

    fun increase(detailBinding: FragmentDetailBinding) {
        orderPiece(detailBinding.foodNum.text.toString().toInt() + 1, detailBinding)
    }

    fun decrease(detailBinding: FragmentDetailBinding) {
        orderPiece(detailBinding.foodNum.text.toString().toInt() - 1, detailBinding)
        if (detailBinding.foodNum.text.toString().toInt() < 2) {
            detailBinding.foodNum.text = "1"
        }
    }

    fun btnAddMinus(detailBinding: FragmentDetailBinding) {
        detailBinding.plus.setOnClickListener { increase(detailBinding) }
        detailBinding.minus.setOnClickListener { decrease(detailBinding) }
    }
/*
    fun searchFood(searchWord: String) {
        fdao.allFood().enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body()?.yemekler
                val search = list?.filter { it.yemek_adi.contains(searchWord, true) }
                foodList.value = search
            }
            override fun onFailure(call: Call<FoodsAnswer>, t: Throwable) {}
        })
    }

    fun sortByPrice() {
        fdao.allFood().enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body()?.yemekler
                val sort = list?.sortedWith(compareBy { it.yemek_fiyat.toInt() })
                foodList.value = sort
            }

            override fun onFailure(call: Call<FoodsAnswer>, t: Throwable) {}
        })
    }

    fun sortByName() {
        fdao.allFood().enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body()?.yemekler
                val sort = list?.sortedWith(compareBy { it.yemek_adi })
                foodList.value = sort
            }

            override fun onFailure(call: Call<FoodsAnswer>, t: Throwable) {}
        })
    }
    */
}


