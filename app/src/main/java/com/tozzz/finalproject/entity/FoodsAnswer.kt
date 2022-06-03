package com.tozzz.finalproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsAnswer(@SerializedName("yemekler") @Expose var yemekler:List<Foods>,
                       @SerializedName("success") @Expose var success:String) {
}