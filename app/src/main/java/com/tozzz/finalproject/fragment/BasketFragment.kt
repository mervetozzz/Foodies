package com.tozzz.finalproject.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.tozzz.finalproject.R
import com.tozzz.finalproject.adapter.BasketAdapter
import com.tozzz.finalproject.databinding.FragmentBasketBinding
import com.tozzz.finalproject.viewmodel.BasketFragmentViewModel

class BasketFragment : Fragment() {
    private lateinit var bindingBasket: FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentViewModel
    private lateinit var adapter: BasketAdapter
    val kullanici_adi = FirebaseAuth.getInstance().currentUser?.email.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingBasket = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        bindingBasket.basketToolbar = "Sepetim"
        bindingBasket.basketFragment = this
        bindingBasket.textView.visibility = View.INVISIBLE

        viewModel.basketList.observe(viewLifecycleOwner) {
            var toplamSonuc = 0
            it.map { toplamSonuc += it.yemek_fiyat * it.yemek_siparis_adet }
            bindingBasket.total.text = toplamSonuc.toString() + " ₺"

            if (viewModel.basketList.value.isNullOrEmpty()) {
                bindingBasket.textView.visibility = View.VISIBLE
            }

            adapter = BasketAdapter(requireContext(), it, viewModel)
            bindingBasket.basketAdapter = adapter
        }

        bindingBasket.button.setOnClickListener {
            if (viewModel.basketList.value.isNullOrEmpty()) {
                Snackbar.make(it, "Lütfen sepete en az 1 adet ürün ekleyin", Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.WHITE)
                    .show()
            } else {
                Navigation.findNavController(it).navigate(R.id.toConfirm)
            }
        }
        return bindingBasket.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadBasket()
    }
}