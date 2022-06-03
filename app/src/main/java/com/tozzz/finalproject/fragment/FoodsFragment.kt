package com.tozzz.finalproject.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.tozzz.finalproject.MainActivity
import com.tozzz.finalproject.R
import com.tozzz.finalproject.adapter.FoodAdapter
import com.tozzz.finalproject.databinding.FragmentFoodsBinding
import com.tozzz.finalproject.viewmodel.BasketFragmentViewModel
import com.tozzz.finalproject.viewmodel.FoodsFragmentViewModel

class FoodsFragment : Fragment(){
    private lateinit var bindingMain: FragmentFoodsBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var viewModel: FoodsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingMain = DataBindingUtil.inflate(inflater, R.layout.fragment_foods, container, false)
        bindingMain.foodFragment = this
        bindingMain.foodToolbar = "Ürünler"
        (activity as AppCompatActivity).setSupportActionBar(bindingMain.toolbarMain)

        viewModel.foodList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter = FoodAdapter(requireContext(), it, viewModel)
                bindingMain.foodAdapter = adapter
            } else {
                Toast.makeText(requireContext(), "FOOD LIST IS EMPTY", Toast.LENGTH_LONG).show()
            }
        }
        return bindingMain.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: FoodsFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout ->{
                FirebaseAuth.getInstance().signOut()
                activity?.onBackPressed()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(context, "Çıkış Yaptınız ${FirebaseAuth.getInstance().currentUser}..", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }


    fun sortByPrice() {
        viewModel.sortByPrice()
    }

    fun sortByName() {
        viewModel.sortByName()
    }
*/
}