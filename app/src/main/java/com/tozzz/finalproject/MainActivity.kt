package com.tozzz.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tozzz.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val timer = object : CountDownTimer(5500, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//            override fun onFinish() {}
//        }
//        timer.start()

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

//        binding.tvForgotPassword.setOnClickListener {
//            startActivity(Intent(this@MainActivity, ForgetPasswordActivity::class.java))
//        }


        binding.login.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.etLEmail.text.toString().trim()) -> {
                    Toast.makeText(this@MainActivity, "Lütfen emailinizi giriniz.", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(binding.etLPassword.text.toString().trim()) -> {
                    Toast.makeText(this@MainActivity, "Lütfen şifrenizi giriniz..", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = binding.etLEmail.text.toString().trim()
                    val password: String = binding.etLPassword.text.toString().trim()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this@MainActivity, "Hoşgeldiniz ${binding.etLEmail.text}.", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@MainActivity, OrderActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@MainActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}
