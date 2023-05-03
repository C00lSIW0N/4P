package com.example.capstone

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.capstone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class TimeTableActivity : AppCompatActivity() {
    private lateinit var tableLayout: TableLayout

    lateinit var addSchedule: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        addSchedule = findViewById(R.id.add_schedule)
        tableLayout = findViewById(R.id.tableLayout)

        // 버튼 클릭시 다이얼로그 띄우기
        addSchedule.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_addschedule)

            val schedulenameid = dialog.findViewById<EditText>(R.id.select_name)
            var schedulename = ""
            val fixbutton = dialog.findViewById<Button>(R.id.add_schedule_confirm)
            val cancelbutton = dialog.findViewById<Button>(R.id.cancel_button)

            // 요일 변수들 체크박스로 바꾼 버전
            val dayselect_mon = dialog.findViewById<CheckBox>(R.id.monday)
            val dayselect_tue = dialog.findViewById<CheckBox>(R.id.tuesday)
            val dayselect_wed = dialog.findViewById<CheckBox>(R.id.wednesday)
            val dayselect_thu = dialog.findViewById<CheckBox>(R.id.thursday)
            val dayselect_fri = dialog.findViewById<CheckBox>(R.id.friday)
            val day = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday")

            val timeselect_9 = dialog.findViewById<CheckBox>(R.id.timeselect_9)
            val timeselect_10 = dialog.findViewById<CheckBox>(R.id.timeselect_10)
            val timeselect_11 = dialog.findViewById<CheckBox>(R.id.timeselect_11)
            val timeselect_12 = dialog.findViewById<CheckBox>(R.id.timeselect_12)
            val timeselect_13 = dialog.findViewById<CheckBox>(R.id.timeselect_13)
            val timeselect_14 = dialog.findViewById<CheckBox>(R.id.timeselect_14)
            val timeselect_15 = dialog.findViewById<CheckBox>(R.id.timeselect_15)
            val timeselect_16 = dialog.findViewById<CheckBox>(R.id.timeselect_16)
            val timeselect_17 = dialog.findViewById<CheckBox>(R.id.timeselect_17)
            val timeselect_18 = dialog.findViewById<CheckBox>(R.id.timeselect_18)
            val timeselect_19 = dialog.findViewById<CheckBox>(R.id.timeselect_19)
            val timeselect_20 = dialog.findViewById<CheckBox>(R.id.timeselect_20)

            var checkedCount = 0 // 선택된 체크박스 개수, 2개 이상 선택 못하도록 막을 거...

            val timeselect = IntArray(12)
            val dayselect = IntArray(5)

            var starttime = -1
            var finishtime = -1

            var selectedday = "value"

            // 이건 일정 이름 받는 함수
            schedulenameid.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    schedulename = s.toString()
                }
                override fun afterTextChanged(s: Editable) { }
            })

            // 이건 요일 받는 함수(체크박스 버전)
            dayselect_mon.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[0] = 1;  selectedday = "temp" }
                else { dayselect[0] = 0;    selectedday = "value" }
            }
            dayselect_tue.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[1] = 1;  selectedday = "temp" }
                else { dayselect[1] = 0;    selectedday = "value" }
            }
            dayselect_wed.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[2] = 1;  selectedday = "temp" }
                else { dayselect[2] = 0;    selectedday = "value" }
            }
            dayselect_thu.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[3] = 1;  selectedday = "temp" }
                else { dayselect[3] = 0;    selectedday = "value" }
            }
            dayselect_fri.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[4] = 1;  selectedday = "temp" }
                else { dayselect[4] = 0;    selectedday = "value" }
            }

            // 체크박스(시간) 함수들 (같은 함수들 묶어서 접을 수 있는 기능 넣어줘 ㅈㅂ 안드로이드)
            timeselect_9.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[0] = 1
                    if (checkedCount > 2) {
                        timeselect_9.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[0] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[0] = 0
                }
            }
            timeselect_10.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[1] = 1
                    if (checkedCount > 2) {
                        timeselect_10.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[1] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[1] = 0
                }
            }
            timeselect_11.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[2] = 1
                    if (checkedCount > 2) {
                        timeselect_11.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[2] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[2] = 0
                }
            }
            timeselect_12.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[3] = 1
                    if (checkedCount > 2) {
                        timeselect_12.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[3] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[3] = 0
                }
            }
            timeselect_13.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[4] = 1
                    if (checkedCount > 2) {
                        timeselect_13.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[4] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[4] = 0
                }
            }
            timeselect_14.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[5] = 1
                    if (checkedCount > 2) {
                        timeselect_14.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[5] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[5] = 0
                }
            }
            timeselect_15.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[6] = 1
                    if (checkedCount > 2) {
                        timeselect_15.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[6] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[6] = 0
                }
            }
            timeselect_16.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[7] = 1
                    if (checkedCount > 2) {
                        timeselect_16.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[7] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[7] = 0
                }
            }
            timeselect_17.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[8] = 1
                    if (checkedCount > 2) {
                        timeselect_17.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[8] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[8] = 0
                }
            }
            timeselect_18.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[9] = 1
                    if (checkedCount > 2) {
                        timeselect_18.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[9] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[9] = 0
                }
            }
            timeselect_19.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[10] = 1
                    if (checkedCount > 2) {
                        timeselect_19.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[10] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[10] = 0
                }
            }
            timeselect_20.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[11] = 1
                    if (checkedCount > 2) {
                        timeselect_20.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[11] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[11] = 0
                }
            }

            // fix 버튼 눌렀을 때 쓰일 함수... 백그라운드 색 글자색 바꾸는 거........
            fun changeTextView(textView: TextView, schedulename: String) {
                val colorcode = "#0066CC"
                textView.text = schedulename
                textView.setBackgroundColor(Color.parseColor(colorcode))
                textView.setTextColor(Color.WHITE)
            }

            fixbutton.setOnClickListener {
                // 여기에다간... 적용하는 거 쓰기... 아마 가기 전까지 못함...
                for (i in 0..11) {
                    if (timeselect[i] == 1) {
                        starttime = i
                        break
                    }
                }
                for (i in (starttime+1)..11) {
                    if (timeselect[i] == 1) {
                        finishtime = i
                        break
                    }
                }

                if (schedulename != "" && selectedday != "value" && starttime != -1 && finishtime != -1) {
                    starttime += 9
                    finishtime += 8
                    // 요일 먼저 for문으로 돌리면서 시간 지정하기

                    for (i in 0..4) {
                        if (dayselect[i] == 1)  {
                            selectedday = day[i]

                            for (i in 0..(finishtime-starttime)) {
                                val tempname = selectedday + (i+starttime).toString()

                                if (tempname == "monday9") { val textView = findViewById<TextView>(R.id.monday9);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday10") { val textView = findViewById<TextView>(R.id.monday10);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday11") { val textView = findViewById<TextView>(R.id.monday11);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday12") { val textView = findViewById<TextView>(R.id.monday12);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday13") { val textView = findViewById<TextView>(R.id.monday13);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday14") { val textView = findViewById<TextView>(R.id.monday14);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday15") { val textView = findViewById<TextView>(R.id.monday15);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday16") { val textView = findViewById<TextView>(R.id.monday16);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday17") { val textView = findViewById<TextView>(R.id.monday17);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday18") { val textView = findViewById<TextView>(R.id.monday18);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday19") { val textView = findViewById<TextView>(R.id.monday19);   changeTextView(textView, schedulename) }
                                else if (tempname == "monday20") { val textView = findViewById<TextView>(R.id.monday20);   changeTextView(textView, schedulename) }

                                else if (tempname == "tuesday9") { val textView = findViewById<TextView>(R.id.tuesday9);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday10") { val textView = findViewById<TextView>(R.id.tuesday10);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday11") { val textView = findViewById<TextView>(R.id.tuesday11);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday12") { val textView = findViewById<TextView>(R.id.tuesday12);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday13") { val textView = findViewById<TextView>(R.id.tuesday13);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday14") { val textView = findViewById<TextView>(R.id.tuesday14);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday15") { val textView = findViewById<TextView>(R.id.tuesday15);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday16") { val textView = findViewById<TextView>(R.id.tuesday16);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday17") { val textView = findViewById<TextView>(R.id.tuesday17);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday18") { val textView = findViewById<TextView>(R.id.tuesday18);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday19") { val textView = findViewById<TextView>(R.id.tuesday19);   changeTextView(textView, schedulename) }
                                else if (tempname == "tuesday20") { val textView = findViewById<TextView>(R.id.tuesday20);   changeTextView(textView, schedulename) }

                                else if (tempname == "wednesday9") { val textView = findViewById<TextView>(R.id.wednesday9);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday10") { val textView = findViewById<TextView>(R.id.wednesday10);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday11") { val textView = findViewById<TextView>(R.id.wednesday11);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday12") { val textView = findViewById<TextView>(R.id.wednesday12);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday13") { val textView = findViewById<TextView>(R.id.wednesday13);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday14") { val textView = findViewById<TextView>(R.id.wednesday14);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday15") { val textView = findViewById<TextView>(R.id.wednesday15);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday16") { val textView = findViewById<TextView>(R.id.wednesday16);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday17") { val textView = findViewById<TextView>(R.id.wednesday17);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday18") { val textView = findViewById<TextView>(R.id.wednesday18);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday19") { val textView = findViewById<TextView>(R.id.wednesday19);   changeTextView(textView, schedulename) }
                                else if (tempname == "wednesday20") { val textView = findViewById<TextView>(R.id.wednesday20);   changeTextView(textView, schedulename) }

                                else if (tempname == "thursday9") { val textView = findViewById<TextView>(R.id.thursday9);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday10") { val textView = findViewById<TextView>(R.id.thursday10);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday11") { val textView = findViewById<TextView>(R.id.thursday11);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday12") { val textView = findViewById<TextView>(R.id.thursday12);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday13") { val textView = findViewById<TextView>(R.id.thursday13);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday14") { val textView = findViewById<TextView>(R.id.thursday14);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday15") { val textView = findViewById<TextView>(R.id.thursday15);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday16") { val textView = findViewById<TextView>(R.id.thursday16);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday17") { val textView = findViewById<TextView>(R.id.thursday17);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday18") { val textView = findViewById<TextView>(R.id.thursday18);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday19") { val textView = findViewById<TextView>(R.id.thursday19);   changeTextView(textView, schedulename) }
                                else if (tempname == "thursday20") { val textView = findViewById<TextView>(R.id.thursday20);   changeTextView(textView, schedulename) }

                                else if (tempname == "friday9") { val textView = findViewById<TextView>(R.id.friday9);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday10") { val textView = findViewById<TextView>(R.id.friday10);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday11") { val textView = findViewById<TextView>(R.id.friday11);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday12") { val textView = findViewById<TextView>(R.id.friday12);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday13") { val textView = findViewById<TextView>(R.id.friday13);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday14") { val textView = findViewById<TextView>(R.id.friday14);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday15") { val textView = findViewById<TextView>(R.id.friday15);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday16") { val textView = findViewById<TextView>(R.id.friday16);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday17") { val textView = findViewById<TextView>(R.id.friday17);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday18") { val textView = findViewById<TextView>(R.id.friday18);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday19") { val textView = findViewById<TextView>(R.id.friday19);   changeTextView(textView, schedulename) }
                                else if (tempname == "friday20") { val textView = findViewById<TextView>(R.id.friday20);   changeTextView(textView, schedulename) }

                            }
                    }
                    }
                    dialog.dismiss()
                }
                else {
                    if (schedulename == "")
                        Toast.makeText(this, "일정 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    else if (selectedday == "value")
                        Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this, "시작과 끝 시간을 모두 선택해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            cancelbutton.setOnClickListener {dialog.dismiss()}
            dialog.show()
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