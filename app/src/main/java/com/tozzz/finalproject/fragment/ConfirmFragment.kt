package com.tozzz.finalproject.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.tozzz.finalproject.MainActivity
import com.tozzz.finalproject.R
import com.tozzz.finalproject.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {
    private lateinit var bindingConfirm : FragmentConfirmBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingConfirm = DataBindingUtil.inflate(inflater,R.layout.fragment_confirm, container, false)
        bindingConfirm.confirmToolbar = "Sipariş Onay"
        (activity as AppCompatActivity).setSupportActionBar(bindingConfirm.toolbarConfirm)
        bindingConfirm.confirmFragment = this
        return bindingConfirm.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

}