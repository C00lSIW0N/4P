package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewBookedActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ViewBooked
    private lateinit var dataList: MutableList<Booked>
    private lateinit var backtotimetable: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        dataList = mutableListOf()

        val db: FirebaseFirestore = Firebase.firestore
        val tempuser = FirebaseAuth.getInstance().currentUser
        var useremail = "email"
        if (tempuser != null) { useremail = tempuser.email.toString() }

        val bookRef = db.collection("user")
        val bookDocument = bookRef.document(useremail)

        bookDocument.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    for (i in 1..100) {
                        val existcheck = document?.get("숙소"+i.toString()+"이름") as String?
                        if (existcheck != null) {
                            val name = document.getString("숙소"+i.toString()+"이름") ?: ""
                            val category = "숙소"
                            val daytime = document.getString("숙소"+i.toString()+"예약날짜") ?: ""

                            val booked = Booked(name, category, daytime)
                            dataList.add(booked)
                        }
                        else { break }
                    }
                    adapter = ViewBooked(dataList)
                    recyclerView.adapter = adapter
                }
            }
            .addOnFailureListener { exception -> }

        backtotimetable = findViewById<Button>(R.id.backtotimetable)
        backtotimetable.setOnClickListener {
            var intent = Intent(this, TimeTableActivity::class.java)
            startActivity(intent)
        }
    }
}
