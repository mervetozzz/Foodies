package com.tozzz.finalproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BasketFoodAnswer(@SerializedName("sepet_yemekler") @Expose var sepet_yemekler: List<Basket>,
                             @SerializedName("success") @Expose var success: Int){
}