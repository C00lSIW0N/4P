package com.example.capstone

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
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

        emailEt = findViewById<EditText>(R.id.emailArea)
        passwordEt = findViewById<EditText>(R.id.passwordArea)
        loginBtn = findViewById<Button>(R.id.loginBtn)
        joinBtn = findViewById<Button>(R.id.joinBtn)

        var sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)

        val userid = sharedPreferences.getString("userid", "")
        val userpw = sharedPreferences.getString("userpw", "")

        if (!userid.isNullOrEmpty() && !userpw.isNullOrEmpty()) {
            auth.signInWithEmailAndPassword(userid.toString(), userpw.toString()) // 로그인
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        // 자동로그인 성공
                        Toast.makeText(this, "자동 로그인 성공", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "자동 로그인 실패", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
        }
        else {
            loginBtn.setOnClickListener {
                var email = emailEt.text.toString()
                var password = passwordEt.text.toString()
                auth.signInWithEmailAndPassword(email, password) // 로그인
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            // 자동로그인 정보 저장
                            val editor = sharedPreferences.edit()
                            editor.putString("userid", email)
                            editor.putString("userpw", password)
                            editor.apply()

                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
            }
        }

        joinBtn.setOnClickListener {
            var intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}