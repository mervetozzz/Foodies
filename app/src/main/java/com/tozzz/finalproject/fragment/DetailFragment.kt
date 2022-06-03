package com.tozzz.finalproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.tozzz.finalproject.R
import com.tozzz.finalproject.databinding.FragmentDetailBinding
import com.tozzz.finalproject.entity.Foods
import com.tozzz.finalproject.viewmodel.DetailFragmentViewModel

class DetailFragment : Fragment() {
    private lateinit var bindingDetail: FragmentDetailBinding
    private lateinit var viewModel: DetailFragmentViewModel
    val kullanici_adi = FirebaseAuth.getInstance().currentUser?.email.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingDetail =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        bindingDetail.detailFragment = this

        val bundle: DetailFragmentArgs by navArgs()
        val selectedFood = bundle.selected

        bindingDetail.foodObject = selectedFood

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${selectedFood.yemek_resim_adi}"
        Picasso.get().load(url).into(bindingDetail.foodPic)

        viewModel.buttonIncDec(bindingDetail)

        return bindingDetail.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun addFood(foodObject: Foods, num: String, it: View) {
        Snackbar.make(
            it,
            "${num} adet ${foodObject.yemek_adi} sepete eklendi...",
            Snackbar.LENGTH_SHORT
        ).show()
        viewModel.add(
            foodObject.yemek_id,
            foodObject.yemek_adi,
            foodObject.yemek_resim_adi,
            foodObject.yemek_fiyat,
            num,
            kullanici_adi
        )
    }
}