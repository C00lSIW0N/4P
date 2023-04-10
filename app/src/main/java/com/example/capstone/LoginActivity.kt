package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.widget.Toast
import com.example.capstone.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var emailEt: EditText
    lateinit var passwordEt: EditText
    lateinit var loginBtn: Button
    lateinit var joinBtn: Button

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.emailArea)
        passwordEt = findViewById(R.id.passwordArea)
        loginBtn = findViewById(R.id.loginBtn)
        joinBtn = findViewById(R.id.joinBtn)

        joinBtn.setOnClickListener {
            var email = emailEt.text.toString()
            var password = passwordEt.text.toString()
            auth.createUserWithEmailAndPassword(email, password) // 회원 가입
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        /*if(auth.currentUser!=null){
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }*/
                    } else {
                        Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        loginBtn.setOnClickListener {
            var email = emailEt.text.toString()
            var password = passwordEt.text.toString()
            auth.signInWithEmailAndPassword(email, password) // 로그인
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
    }
}