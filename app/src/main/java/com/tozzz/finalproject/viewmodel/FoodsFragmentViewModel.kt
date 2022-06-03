package com.tozzz.finalproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tozzz.finalproject.entity.Foods
import com.tozzz.finalproject.repo.FoodsDaoRepository

class FoodsFragmentViewModel : ViewModel() {
    val frepo = FoodsDaoRepository()
    var foodList = MutableLiveData<List<Foods>>()

    init {
        loadFoods()
        foodList = frepo.getFoods()
    }

    fun loadFoods() {
        frepo.allFoods()
    }
/*
    fun search(find: String) {
        frepo.searchFood(find)
    }

    fun sortByPrice() {
        frepo.sortByPrice()
    }

    fun sortByName() {
        frepo.sortByName()
    }
*/

}