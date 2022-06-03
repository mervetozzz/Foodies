package com.tozzz.finalproject.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDFoodAnsw(@SerializedName("success") @Expose var success:Int,
                        @SerializedName("message") @Expose var message:String){
}