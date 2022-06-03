package com.tozzz.finalproject.retrofit

import com.tozzz.finalproject.entity.BasketFoodAnswer
import com.tozzz.finalproject.entity.CRUDFoodAnsw
import com.tozzz.finalproject.entity.FoodsAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFood(): Call<FoodsAnswer>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addBasket(@Field("sepet_yemek_id") yemek_id: String,
                  @Field("yemek_adi") yemek_adi: String,
                  @Field("yemek_resim_adi") yemek_resim_adi: String,
                  @Field("yemek_fiyat") yemek_fiyat: String,
                  @Field("yemek_siparis_adet") yemek_siparis_adet: String,
                  @Field("kullanici_adi") kullanici_adi: String): Call<CRUDFoodAnsw>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun basket(@Field("kullanici_adi") kullanici_adi: String): Call<BasketFoodAnswer>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteOrder(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                    @Field("kullanici_adi") kullanici_adi: String): Call<CRUDFoodAnsw>
}
