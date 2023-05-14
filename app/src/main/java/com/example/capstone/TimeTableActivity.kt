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
import android.view.WindowManager
import android.widget.*
import com.example.capstone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class TimeTableActivity : AppCompatActivity() {
    private lateinit var tableLayout: TableLayout

    lateinit var addSchedule: Button
    lateinit var deleteAllSchedule: Button

    lateinit var monday9: Button
    lateinit var monday10: Button
    lateinit var monday11: Button
    lateinit var monday12: Button
    lateinit var monday13: Button
    lateinit var monday14: Button
    lateinit var monday15: Button
    lateinit var monday16: Button
    lateinit var monday17: Button
    lateinit var monday18: Button
    lateinit var monday19: Button
    lateinit var monday20: Button

    lateinit var tuesday9: Button
    lateinit var tuesday10: Button
    lateinit var tuesday11: Button
    lateinit var tuesday12: Button
    lateinit var tuesday13: Button
    lateinit var tuesday14: Button
    lateinit var tuesday15: Button
    lateinit var tuesday16: Button
    lateinit var tuesday17: Button
    lateinit var tuesday18: Button
    lateinit var tuesday19: Button
    lateinit var tuesday20: Button

    lateinit var wednesday9: Button
    lateinit var wednesday10: Button
    lateinit var wednesday11: Button
    lateinit var wednesday12: Button
    lateinit var wednesday13: Button
    lateinit var wednesday14: Button
    lateinit var wednesday15: Button
    lateinit var wednesday16: Button
    lateinit var wednesday17: Button
    lateinit var wednesday18: Button
    lateinit var wednesday19: Button
    lateinit var wednesday20: Button

    lateinit var thursday9: Button
    lateinit var thursday10: Button
    lateinit var thursday11: Button
    lateinit var thursday12: Button
    lateinit var thursday13: Button
    lateinit var thursday14: Button
    lateinit var thursday15: Button
    lateinit var thursday16: Button
    lateinit var thursday17: Button
    lateinit var thursday18: Button
    lateinit var thursday19: Button
    lateinit var thursday20: Button

    lateinit var friday9: Button
    lateinit var friday10: Button
    lateinit var friday11: Button
    lateinit var friday12: Button
    lateinit var friday13: Button
    lateinit var friday14: Button
    lateinit var friday15: Button
    lateinit var friday16: Button
    lateinit var friday17: Button
    lateinit var friday18: Button
    lateinit var friday19: Button
    lateinit var friday20: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        addSchedule = findViewById(R.id.add_schedule)
        deleteAllSchedule = findViewById(R.id.delete_allschedules)
        tableLayout = findViewById(R.id.tableLayout)

        fun initplan(button: Button) {
            var textcolorcode = "#7bbfea"
            button.text = "-"
            button.setBackgroundColor(Color.WHITE)
            button.setTextColor(Color.parseColor(textcolorcode))
        }

        fun editplan(button: Button, day: String, time: String) {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_editschedule)

            val previous_day = dialog.findViewById<TextView>(R.id.previous_day)
            val reschedulenameid = dialog.findViewById<EditText>(R.id.reschedule_name)
            reschedulenameid.hint = button.text
            var reschedulename = "-"
            val rescheduleconfirmbutton = dialog.findViewById<Button>(R.id.reschedule_confirm)
            val deleteschedulebutton = dialog.findViewById<Button>(R.id.delete_schedule)
            val cancelreschedule = dialog.findViewById<Button>(R.id.cancel_reschedule)

            previous_day.text = day + ", " + time + "의 일정 수정"

            // 이건 일정 이름 받는 함수
            reschedulenameid.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    reschedulename = s.toString()
                }
                override fun afterTextChanged(s: Editable) { }
            })

            rescheduleconfirmbutton.setOnClickListener  {
                var textcolorcode = "#ffffff"
                var backgroundcolorcode = "#0066CC"
                button.setBackgroundColor(Color.parseColor(backgroundcolorcode))
                button.text = reschedulename
                if (reschedulename == "-") {
                    textcolorcode = "#7bbfea"
                    button.setBackgroundColor(Color.WHITE)
                }
                else if (reschedulename == "") {
                    dialog.dismiss()
                }
                button.setTextColor(Color.parseColor(textcolorcode))
                dialog.dismiss()
            }

            deleteschedulebutton.setOnClickListener {
                var textcolorcode = "#7bbfea"
                button.setBackgroundColor(Color.WHITE)
                button.text = "-"
                button.setTextColor(Color.parseColor(textcolorcode))
                // initplan(button)
                dialog.dismiss()
            }

            cancelreschedule.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        // 여기부터 각 시간표 별 버튼들 (월화수목금/시간표/시간 표시된건 온클릭리스너 안쓸거임!!!!)
        monday9 = findViewById<Button>(R.id.monday9)
        monday10 = findViewById<Button>(R.id.monday10)
        monday11 = findViewById<Button>(R.id.monday11)
        monday12 = findViewById<Button>(R.id.monday12)
        monday13 = findViewById<Button>(R.id.monday13)
        monday14 = findViewById<Button>(R.id.monday14)
        monday15 = findViewById<Button>(R.id.monday15)
        monday16 = findViewById<Button>(R.id.monday16)
        monday17 = findViewById<Button>(R.id.monday17)
        monday18 = findViewById<Button>(R.id.monday18)
        monday19 = findViewById<Button>(R.id.monday19)
        monday20 = findViewById<Button>(R.id.monday20)

        tuesday9 = findViewById<Button>(R.id.tuesday9)
        tuesday10 = findViewById<Button>(R.id.tuesday10)
        tuesday11 = findViewById<Button>(R.id.tuesday11)
        tuesday12 = findViewById<Button>(R.id.tuesday12)
        tuesday13 = findViewById<Button>(R.id.tuesday13)
        tuesday14 = findViewById<Button>(R.id.tuesday14)
        tuesday15 = findViewById<Button>(R.id.tuesday15)
        tuesday16 = findViewById<Button>(R.id.tuesday16)
        tuesday17 = findViewById<Button>(R.id.tuesday17)
        tuesday18 = findViewById<Button>(R.id.tuesday18)
        tuesday19 = findViewById<Button>(R.id.tuesday19)
        tuesday20 = findViewById<Button>(R.id.tuesday20)

        wednesday9 = findViewById<Button>(R.id.wednesday9)
        wednesday10 = findViewById<Button>(R.id.wednesday10)
        wednesday11 = findViewById<Button>(R.id.wednesday11)
        wednesday12 = findViewById<Button>(R.id.wednesday12)
        wednesday13 = findViewById<Button>(R.id.wednesday13)
        wednesday14 = findViewById<Button>(R.id.wednesday14)
        wednesday15 = findViewById<Button>(R.id.wednesday15)
        wednesday16 = findViewById<Button>(R.id.wednesday16)
        wednesday17 = findViewById<Button>(R.id.wednesday17)
        wednesday18 = findViewById<Button>(R.id.wednesday18)
        wednesday19 = findViewById<Button>(R.id.wednesday19)
        wednesday20 = findViewById<Button>(R.id.wednesday20)

        thursday9 = findViewById<Button>(R.id.thursday9)
        thursday10 = findViewById<Button>(R.id.thursday10)
        thursday11 = findViewById<Button>(R.id.thursday11)
        thursday12 = findViewById<Button>(R.id.thursday12)
        thursday13 = findViewById<Button>(R.id.thursday13)
        thursday14 = findViewById<Button>(R.id.thursday14)
        thursday15 = findViewById<Button>(R.id.thursday15)
        thursday16 = findViewById<Button>(R.id.thursday16)
        thursday17 = findViewById<Button>(R.id.thursday17)
        thursday18 = findViewById<Button>(R.id.thursday18)
        thursday19 = findViewById<Button>(R.id.thursday19)
        thursday20 = findViewById<Button>(R.id.thursday20)

        friday9 = findViewById<Button>(R.id.friday9)
        friday10 = findViewById<Button>(R.id.friday10)
        friday11 = findViewById<Button>(R.id.friday11)
        friday12 = findViewById<Button>(R.id.friday12)
        friday13 = findViewById<Button>(R.id.friday13)
        friday14 = findViewById<Button>(R.id.friday14)
        friday15 = findViewById<Button>(R.id.friday15)
        friday16 = findViewById<Button>(R.id.friday16)
        friday17 = findViewById<Button>(R.id.friday17)
        friday18= findViewById<Button>(R.id.friday18)
        friday19 = findViewById<Button>(R.id.friday19)
        friday20 = findViewById<Button>(R.id.friday20)
        // 시간표를 전부 버튼으로 바꿔서... 그것도 해둬야함... (버튼 클릭하면 그 시간의 일정 수정/삭제할 수 있게...)/ 완료함

        // 일정 추가 버튼 다이얼로그
        addSchedule.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_addschedule)

            /*
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            */

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
            val timeselect_21 = dialog.findViewById<CheckBox>(R.id.timeselect_21)

            var checkedCount = 0 // 선택된 체크박스 개수, 2개 이상 선택 못하도록 막을 거...

            val timeselect = IntArray(13)
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
            timeselect_21.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    checkedCount++
                    timeselect[12] = 1
                    if (checkedCount > 2) {
                        timeselect_21.isChecked = false // 선택 취소
                        checkedCount--
                        timeselect[12] = 0
                    }
                } else {
                    checkedCount--
                    timeselect[12] = 0
                }
            }

            // fix 버튼 눌렀을 때 쓰일 함수... 백그라운드 색 글자색 바꾸는 거........
            fun changebutton(button: Button, schedulename: String) {
                val colorcode = "#0066CC"
                button.text = schedulename
                button.setBackgroundColor(Color.parseColor(colorcode))
                button.setTextColor(Color.WHITE)
            }

            fixbutton.setOnClickListener {
                // 여기에다간... 적용하는 거 쓰기... 아마 가기 전까지 못함...
                for (i in 0..12) {
                    if (timeselect[i] == 1) {
                        starttime = i
                        break
                    }
                }
                for (i in (starttime+1)..12) {
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

                                if (tempname == "monday9") { val button = findViewById<Button>(R.id.monday9);   changebutton(button, schedulename) }
                                else if (tempname == "monday10") { val button = findViewById<Button>(R.id.monday10);   changebutton(button, schedulename) }
                                else if (tempname == "monday11") { val button = findViewById<Button>(R.id.monday11);   changebutton(button, schedulename) }
                                else if (tempname == "monday12") { val button = findViewById<Button>(R.id.monday12);   changebutton(button, schedulename) }
                                else if (tempname == "monday13") { val button = findViewById<Button>(R.id.monday13);   changebutton(button, schedulename) }
                                else if (tempname == "monday14") { val button = findViewById<Button>(R.id.monday14);   changebutton(button, schedulename) }
                                else if (tempname == "monday15") { val button = findViewById<Button>(R.id.monday15);   changebutton(button, schedulename) }
                                else if (tempname == "monday16") { val button = findViewById<Button>(R.id.monday16);   changebutton(button, schedulename) }
                                else if (tempname == "monday17") { val button = findViewById<Button>(R.id.monday17);   changebutton(button, schedulename) }
                                else if (tempname == "monday18") { val button = findViewById<Button>(R.id.monday18);   changebutton(button, schedulename) }
                                else if (tempname == "monday19") { val button = findViewById<Button>(R.id.monday19);   changebutton(button, schedulename) }
                                else if (tempname == "monday20") { val button = findViewById<Button>(R.id.monday20);   changebutton(button, schedulename) }

                                else if (tempname == "tuesday9") { val button = findViewById<Button>(R.id.tuesday9);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday10") { val button = findViewById<Button>(R.id.tuesday10);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday11") { val button = findViewById<Button>(R.id.tuesday11);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday12") { val button = findViewById<Button>(R.id.tuesday12);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday13") { val button = findViewById<Button>(R.id.tuesday13);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday14") { val button = findViewById<Button>(R.id.tuesday14);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday15") { val button = findViewById<Button>(R.id.tuesday15);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday16") { val button = findViewById<Button>(R.id.tuesday16);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday17") { val button = findViewById<Button>(R.id.tuesday17);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday18") { val button = findViewById<Button>(R.id.tuesday18);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday19") { val button = findViewById<Button>(R.id.tuesday19);   changebutton(button, schedulename) }
                                else if (tempname == "tuesday20") { val button = findViewById<Button>(R.id.tuesday20);   changebutton(button, schedulename) }

                                else if (tempname == "wednesday9") { val button = findViewById<Button>(R.id.wednesday9);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday10") { val button = findViewById<Button>(R.id.wednesday10);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday11") { val button = findViewById<Button>(R.id.wednesday11);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday12") { val button = findViewById<Button>(R.id.wednesday12);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday13") { val button = findViewById<Button>(R.id.wednesday13);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday14") { val button = findViewById<Button>(R.id.wednesday14);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday15") { val button = findViewById<Button>(R.id.wednesday15);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday16") { val button = findViewById<Button>(R.id.wednesday16);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday17") { val button = findViewById<Button>(R.id.wednesday17);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday18") { val button = findViewById<Button>(R.id.wednesday18);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday19") { val button = findViewById<Button>(R.id.wednesday19);   changebutton(button, schedulename) }
                                else if (tempname == "wednesday20") { val button = findViewById<Button>(R.id.wednesday20);   changebutton(button, schedulename) }

                                else if (tempname == "thursday9") { val button = findViewById<Button>(R.id.thursday9);   changebutton(button, schedulename) }
                                else if (tempname == "thursday10") { val button = findViewById<Button>(R.id.thursday10);   changebutton(button, schedulename) }
                                else if (tempname == "thursday11") { val button = findViewById<Button>(R.id.thursday11);   changebutton(button, schedulename) }
                                else if (tempname == "thursday12") { val button = findViewById<Button>(R.id.thursday12);   changebutton(button, schedulename) }
                                else if (tempname == "thursday13") { val button = findViewById<Button>(R.id.thursday13);   changebutton(button, schedulename) }
                                else if (tempname == "thursday14") { val button = findViewById<Button>(R.id.thursday14);   changebutton(button, schedulename) }
                                else if (tempname == "thursday15") { val button = findViewById<Button>(R.id.thursday15);   changebutton(button, schedulename) }
                                else if (tempname == "thursday16") { val button = findViewById<Button>(R.id.thursday16);   changebutton(button, schedulename) }
                                else if (tempname == "thursday17") { val button = findViewById<Button>(R.id.thursday17);   changebutton(button, schedulename) }
                                else if (tempname == "thursday18") { val button = findViewById<Button>(R.id.thursday18);   changebutton(button, schedulename) }
                                else if (tempname == "thursday19") { val button = findViewById<Button>(R.id.thursday19);   changebutton(button, schedulename) }
                                else if (tempname == "thursday20") { val button = findViewById<Button>(R.id.thursday20);   changebutton(button, schedulename) }

                                else if (tempname == "friday9") { val button = findViewById<Button>(R.id.friday9);   changebutton(button, schedulename) }
                                else if (tempname == "friday10") { val button = findViewById<Button>(R.id.friday10);   changebutton(button, schedulename) }
                                else if (tempname == "friday11") { val button = findViewById<Button>(R.id.friday11);   changebutton(button, schedulename) }
                                else if (tempname == "friday12") { val button = findViewById<Button>(R.id.friday12);   changebutton(button, schedulename) }
                                else if (tempname == "friday13") { val button = findViewById<Button>(R.id.friday13);   changebutton(button, schedulename) }
                                else if (tempname == "friday14") { val button = findViewById<Button>(R.id.friday14);   changebutton(button, schedulename) }
                                else if (tempname == "friday15") { val button = findViewById<Button>(R.id.friday15);   changebutton(button, schedulename) }
                                else if (tempname == "friday16") { val button = findViewById<Button>(R.id.friday16);   changebutton(button, schedulename) }
                                else if (tempname == "friday17") { val button = findViewById<Button>(R.id.friday17);   changebutton(button, schedulename) }
                                else if (tempname == "friday18") { val button = findViewById<Button>(R.id.friday18);   changebutton(button, schedulename) }
                                else if (tempname == "friday19") { val button = findViewById<Button>(R.id.friday19);   changebutton(button, schedulename) }
                                else if (tempname == "friday20") { val button = findViewById<Button>(R.id.friday20);   changebutton(button, schedulename) }

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
        // 일정 전체 삭제 버튼 다이얼로그
        deleteAllSchedule.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_deleteallschedules)

            val deleteall_confirm = dialog.findViewById<Button>(R.id.deleteall_confirm)
            val deleteall_cancel = dialog.findViewById<Button>(R.id.deleteall_cancel)

            deleteall_confirm.setOnClickListener  {
                initplan(monday9)
                initplan(monday10)
                initplan(monday11)
                initplan(monday12)
                initplan(monday13)
                initplan(monday14)
                initplan(monday15)
                initplan(monday16)
                initplan(monday17)
                initplan(monday18)
                initplan(monday19)
                initplan(monday20)

                initplan(tuesday9)
                initplan(tuesday10)
                initplan(tuesday11)
                initplan(tuesday12)
                initplan(tuesday13)
                initplan(tuesday14)
                initplan(tuesday15)
                initplan(tuesday16)
                initplan(tuesday17)
                initplan(tuesday18)
                initplan(tuesday19)
                initplan(tuesday20)

                initplan(wednesday9)
                initplan(wednesday10)
                initplan(wednesday11)
                initplan(wednesday12)
                initplan(wednesday13)
                initplan(wednesday14)
                initplan(wednesday15)
                initplan(wednesday16)
                initplan(wednesday17)
                initplan(wednesday18)
                initplan(wednesday19)
                initplan(wednesday20)

                initplan(thursday9)
                initplan(thursday10)
                initplan(thursday11)
                initplan(thursday12)
                initplan(thursday13)
                initplan(thursday14)
                initplan(thursday15)
                initplan(thursday16)
                initplan(thursday17)
                initplan(thursday18)
                initplan(thursday19)
                initplan(thursday20)

                initplan(friday9)
                initplan(friday10)
                initplan(friday11)
                initplan(friday12)
                initplan(friday13)
                initplan(friday14)
                initplan(friday15)
                initplan(friday16)
                initplan(friday17)
                initplan(friday18)
                initplan(friday19)
                initplan(friday20)

                Toast.makeText(this, "모든 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }
            deleteall_cancel.setOnClickListener { dialog.dismiss() }

            dialog.show()
        }

        // 각 버튼 별 시간표 일정 수정/삭제 버튼
        monday9.setOnClickListener  {editplan(monday9, "월요일", "9시~10시")}
        monday10.setOnClickListener  {editplan(monday10, "월요일", "10시~11시")}
        monday11.setOnClickListener  {editplan(monday11, "월요일", "11시~12시")}
        monday12.setOnClickListener  {editplan(monday12, "월요일", "12시~13시")}
        monday13.setOnClickListener  {editplan(monday13, "월요일", "13시~14시")}
        monday14.setOnClickListener  {editplan(monday14, "월요일", "14시~15시")}
        monday15.setOnClickListener  {editplan(monday15, "월요일", "15시~16시")}
        monday16.setOnClickListener  {editplan(monday16, "월요일", "16시~17시")}
        monday17.setOnClickListener  {editplan(monday17, "월요일", "17시~18시")}
        monday18.setOnClickListener  {editplan(monday18, "월요일", "18시~19시")}
        monday19.setOnClickListener  {editplan(monday19, "월요일", "19시~20시")}
        monday20.setOnClickListener  {editplan(monday20, "월요일", "20시~21시")}

        tuesday9.setOnClickListener  {editplan(tuesday9, "화요일", "9시~10시")}
        tuesday10.setOnClickListener  {editplan(tuesday10, "화요일", "10시~11시")}
        tuesday11.setOnClickListener  {editplan(tuesday11, "화요일", "11시~12시")}
        tuesday12.setOnClickListener  {editplan(tuesday12, "화요일", "12시~13시")}
        tuesday13.setOnClickListener  {editplan(tuesday13, "화요일", "13시~14시")}
        tuesday14.setOnClickListener  {editplan(tuesday14, "화요일", "14시~15시")}
        tuesday15.setOnClickListener  {editplan(tuesday15, "화요일", "15시~16시")}
        tuesday16.setOnClickListener  {editplan(tuesday16, "화요일", "16시~17시")}
        tuesday17.setOnClickListener  {editplan(tuesday17, "화요일", "17시~18시")}
        tuesday18.setOnClickListener  {editplan(tuesday18, "화요일", "18시~19시")}
        tuesday19.setOnClickListener  {editplan(tuesday19, "화요일", "19시~20시")}
        tuesday20.setOnClickListener  {editplan(tuesday20, "화요일", "20시~21시")}

        wednesday9.setOnClickListener  {editplan(wednesday9, "수요일", "9시~10시")}
        wednesday10.setOnClickListener  {editplan(wednesday10, "수요일", "10시~11시")}
        wednesday11.setOnClickListener  {editplan(wednesday11, "수요일", "11시~12시")}
        wednesday12.setOnClickListener  {editplan(wednesday12, "수요일", "12시~13시")}
        wednesday13.setOnClickListener  {editplan(wednesday13, "수요일", "13시~14시")}
        wednesday14.setOnClickListener  {editplan(wednesday14, "수요일", "14시~15시")}
        wednesday15.setOnClickListener  {editplan(wednesday15, "수요일", "15시~16시")}
        wednesday16.setOnClickListener  {editplan(wednesday16, "수요일", "16시~17시")}
        wednesday17.setOnClickListener  {editplan(wednesday17, "수요일", "17시~18시")}
        wednesday18.setOnClickListener  {editplan(wednesday18, "수요일", "18시~19시")}
        wednesday19.setOnClickListener  {editplan(wednesday19, "수요일", "19시~20시")}
        wednesday20.setOnClickListener  {editplan(wednesday20, "수요일", "20시~21시")}

        thursday9.setOnClickListener  {editplan(thursday9, "목요일", "9시~10시")}
        thursday10.setOnClickListener  {editplan(thursday10, "목요일", "10시~11시")}
        thursday11.setOnClickListener  {editplan(thursday11, "목요일", "11시~12시")}
        thursday12.setOnClickListener  {editplan(thursday12, "목요일", "12시~13시")}
        thursday13.setOnClickListener  {editplan(thursday13, "목요일", "13시~14시")}
        thursday14.setOnClickListener  {editplan(thursday14, "목요일", "14시~15시")}
        thursday15.setOnClickListener  {editplan(thursday15, "목요일", "15시~16시")}
        thursday16.setOnClickListener  {editplan(thursday16, "목요일", "16시~17시")}
        thursday17.setOnClickListener  {editplan(thursday17, "목요일", "17시~18시")}
        thursday18.setOnClickListener  {editplan(thursday18, "목요일", "18시~19시")}
        thursday19.setOnClickListener  {editplan(thursday19, "목요일", "19시~20시")}
        thursday20.setOnClickListener  {editplan(thursday20, "목요일", "20시~21시")}

        friday9.setOnClickListener  {editplan(friday9, "금요일", "9시~10시")}
        friday10.setOnClickListener  {editplan(friday10, "금요일", "10시~11시")}
        friday11.setOnClickListener  {editplan(friday11, "금요일", "11시~12시")}
        friday12.setOnClickListener  {editplan(friday12, "금요일", "12시~13시")}
        friday13.setOnClickListener  {editplan(friday13, "금요일", "13시~14시")}
        friday14.setOnClickListener  {editplan(friday14, "금요일", "14시~15시")}
        friday15.setOnClickListener  {editplan(friday15, "금요일", "15시~16시")}
        friday16.setOnClickListener  {editplan(friday16, "금요일", "16시~17시")}
        friday17.setOnClickListener  {editplan(friday17, "금요일", "17시~18시")}
        friday18.setOnClickListener  {editplan(friday18, "금요일", "18시~19시")}
        friday19.setOnClickListener  {editplan(friday19, "금요일", "19시~20시")}
        friday20.setOnClickListener  {editplan(friday20, "금요일", "20시~21시")}
        

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