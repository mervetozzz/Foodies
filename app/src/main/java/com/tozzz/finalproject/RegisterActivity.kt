package com.tozzz.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tozzz.finalproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            when {
                TextUtils.isEmpty(binding.etEmail.text.toString().trim()) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Lütfen email adresinizi giriniz...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.etPassword.text.toString().trim()) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "LÜtfen bi şifre giriniz...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {

                    val email: String = binding.etEmail.text.toString().trim()
                    val password: String = binding.etPassword.text.toString().trim()
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Kayıt başarılı ${binding.etEmail.text}...",
                                        Toast.LENGTH_SHORT
                                    ).show()


                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })

                }
            }
        }
    }
}