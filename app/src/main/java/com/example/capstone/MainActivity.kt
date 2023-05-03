package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.util.Log
import android.widget.*
import com.example.capstone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var logoutBtn: Button
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        logoutBtn = findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            //firebase auth에서 sign out 기능 호출
            auth.signOut()
            Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
            var intent=Intent(this,LoginActivity::class.java) //로그인 페이지 이동
            startActivity(intent)
            this?.finish()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_timetable -> {
                    val intent = Intent(this, TimeTableActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


    }
}