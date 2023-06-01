package com.example.capstone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class ViewBookedActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ViewBooked
    private lateinit var dataList: MutableList<Booked>
    private lateinit var backtotimetable: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked)

        val all = findViewById<CheckBox>(R.id.all)
        val accommodation = findViewById<CheckBox>(R.id.accommodation)
        val restaurant = findViewById<CheckBox>(R.id.restaurant)
        val place = findViewById<CheckBox>(R.id.place)
        val rentcar = findViewById<CheckBox>(R.id.rentcar)

        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        dataList = mutableListOf()
        var filteredDataList: List<Booked> = mutableListOf()

        val db: FirebaseFirestore = Firebase.firestore
        val tempuser = FirebaseAuth.getInstance().currentUser
        var useremail = "email"
        if (tempuser != null) {
            useremail = tempuser.email.toString()
        }

        val bookRef = db.collection("user")
        val bookDocument = bookRef.document(useremail)

        bookDocument.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    fun addBooked (type: String) {
                        for (i in 1..100) {
                            val existcheck = document?.get(type+i.toString()+"이름") as String?
                            if (existcheck != null) {
                                val name = document.getString(type+i.toString()+"이름") ?: ""
                                val category = type
                                val daytime = document.getString(type+i.toString()+"예약날짜") ?: ""

                                val booked = Booked(name, category, daytime)
                                dataList.add(booked)
                            }
                        }
                    }

                    addBooked("숙소")
                    addBooked("식당")
                    addBooked("관광명소")
                    addBooked("렌트카")

                    adapter = ViewBooked(dataList)
                    recyclerView.adapter = adapter
                }
            }
            .addOnFailureListener { Toast.makeText(this, "예약 내역 불러오기 실패", Toast.LENGTH_SHORT).show() }

        val updateFilteredDataList: () -> Unit = {
            filteredDataList = dataList.filter { item ->
                all.isChecked ||
                        (accommodation.isChecked && item.category == "숙소") ||
                        (restaurant.isChecked && item.category == "식당") ||
                        (place.isChecked && item.category == "관광명소") ||
                        (rentcar.isChecked && item.category == "렌트카")
            }

            adapter = ViewBooked(filteredDataList)
            recyclerView.adapter = adapter
        }

        all.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                accommodation.isChecked = false
                restaurant.isChecked = false
                place.isChecked = false
                rentcar.isChecked = false
            }
            if (!accommodation.isChecked && !restaurant.isChecked && !place.isChecked && !rentcar.isChecked) { all.isChecked = true }
            updateFilteredDataList()
        }

        accommodation.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                all.isChecked = false
            } else {
                if (!restaurant.isChecked && !place.isChecked && !rentcar.isChecked) {
                    all.isChecked = true
                }
            }
            updateFilteredDataList()
        }
        restaurant.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                all.isChecked = false
            } else {
                if (!accommodation.isChecked && !place.isChecked && !rentcar.isChecked) {
                    all.isChecked = true
                }
            }
            updateFilteredDataList()
        }
        place.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                all.isChecked = false
            } else {
                if (!accommodation.isChecked && !restaurant.isChecked && !rentcar.isChecked) {
                    all.isChecked = true
                }
            }
            updateFilteredDataList()
        }
        rentcar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                all.isChecked = false
            } else {
                if (!accommodation.isChecked && !restaurant.isChecked && !place.isChecked) {
                    all.isChecked = true
                }
            }
            updateFilteredDataList()
        }

        backtotimetable = findViewById<Button>(R.id.backtotimetable)
        backtotimetable.setOnClickListener {
            val intent = Intent(this, TimeTableActivity::class.java)
            startActivity(intent)
        }
    }
}