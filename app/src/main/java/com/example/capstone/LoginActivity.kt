package com.example.capstone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.capstone.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import android.view.inputmethod.InputMethodManager

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

        joinBtn.setOnClickListener {
            var email = emailEt.text.toString()
            var password = passwordEt.text.toString()
            auth.createUserWithEmailAndPassword(email, password) // 회원 가입
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                        // 파이어베이스에 필드 만들고 모든 시간표를 -로 지정하기... 힘들다
                        val db: FirebaseFirestore = Firebase.firestore
                        val userRef = db.collection("user-timetable")

                        val tempUser = userRef.document(email)

                        tempUser.set(mapOf(
                            "monday9" to "-", "monday10" to "-", "monday11" to "-", "monday12" to "-", "monday13" to "-", "monday14" to "-", "monday15" to "-", "monday16" to "-", "monday17" to "-", "monday18" to "-", "monday19" to "-", "monday20" to "-",
                            "tuesday9" to "-", "tuesday10" to "-", "tuesday11" to "-", "tuesday12" to "-", "tuesday13" to "-", "tuesday14" to "-", "tuesday15" to "-", "tuesday16" to "-", "tuesday17" to "-", "tuesday18" to "-", "tuesday19" to "-", "tuesday20" to "-",
                            "wednesday9" to "-", "wednesday10" to "-", "wednesday11" to "-", "wednesday12" to "-", "wednesday13" to "-", "wednesday14" to "-", "wednesday15" to "-", "wednesday16" to "-", "wednesday17" to "-", "wednesday18" to "-", "wednesday19" to "-", "wednesday20" to "-",
                            "thursday9" to "-", "thursday10" to "-", "thursday11" to "-", "thursday12" to "-", "thursday13" to "-", "thursday14" to "-", "thursday15" to "-", "thursday16" to "-", "thursday17" to "-", "thursday18" to "-", "thursday19" to "-", "thursday20" to "-",
                            "friday9" to "-", "friday10" to "-", "friday11" to "-", "friday12" to "-", "friday13" to "-", "friday14" to "-", "friday15" to "-", "friday16" to "-", "friday17" to "-", "friday18" to "-", "friday19" to "-", "friday20" to "-"
                            ))
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