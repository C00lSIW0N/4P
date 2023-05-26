package com.example.capstone

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import android.view.inputmethod.InputMethodManager


class TimeTableActivity : AppCompatActivity() {
    private lateinit var tableLayout: TableLayout

    lateinit var addSchedule: Button
    lateinit var deleteAllSchedule: Button
    lateinit var viewBooked: Button
    lateinit var viewCart: Button

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
        viewBooked = findViewById(R.id.viewbooked)
        viewCart = findViewById(R.id.viewCart)

        tableLayout = findViewById(R.id.tableLayout)

        fun initplan(button: Button) {
            var textcolorcode = "#bbbbbb"
            button.text = " "
            button.textSize = 16f
            button.setBackgroundResource(R.drawable.timetable_cell)
            button.setTextColor(Color.parseColor(textcolorcode))
        }

        // 버튼 백그라운드 색 글자색 바꾸는 함수
        fun changebutton(button: Button, schedulename: String, color: Int) {
            // color가 0이면 월욜, 1이면 화욜... 4이면 금욜
            if (schedulename != " ") {
                var colorcode = "#000000"
                val length = schedulename.length

                if (length > 0 && length < 7) {button.textSize = 16f}
                else if (length < 8) {button.textSize = 14f}
                else if (length < 9) {button.textSize = 12f}
                else if (length < 10) {button.textSize = 11f}
                else if (length < 11) {button.textSize = 10f}
                else if (length < 12) {button.textSize = 9f}
                else {button.textSize = 8f}

                if (color == 0) { colorcode = "#f08676" }
                else if (color == 1) { colorcode = "#ecc369" }
                else if (color == 2) { colorcode = "#a7ca70" }
                else if (color == 3) { colorcode = "#7aa5e9" }
                else { colorcode = "#9f86e1" }

                button.text = schedulename
                button.setBackgroundColor(Color.parseColor(colorcode))
                button.setTextColor(Color.WHITE)
            }
            else { initplan(button) }
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

        // 기본적으로 파이어스토어에 있던 것 불러오는 함수 쓸거임
        val db: FirebaseFirestore = Firebase.firestore
        val tempuser = FirebaseAuth.getInstance().currentUser
        var useremail = "email"
        if (tempuser != null) { useremail = tempuser.email.toString() }

        val userRef = db.collection("user-timetable")
        val userDocument = userRef.document(useremail)

        val bookRef = db.collection("user")
        val bookDocument = bookRef.document(useremail)

        // 온크리에이트에서 시간표 다 불러오고 버튼 텍스트/색 교체, 변경되는 건 따로 함수가 있음 (addplan 그대로 써도 될 듯)
        userDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data
                    // data 맵의 내용 불러오기... null 이 아니라고 확언해놔서 나중에 일정 추가 버튼쪽 함수 null 안 들어가게 해야 할 듯
                    changebutton(monday9, data!!["monday9"] as String, 0)
                    changebutton(monday10, data!!["monday10"] as String, 0)
                    changebutton(monday11, data!!["monday11"] as String, 0)
                    changebutton(monday12, data!!["monday12"] as String, 0)
                    changebutton(monday13, data!!["monday13"] as String, 0)
                    changebutton(monday14, data!!["monday14"] as String, 0)
                    changebutton(monday15, data!!["monday15"] as String, 0)
                    changebutton(monday16, data!!["monday16"] as String, 0)
                    changebutton(monday17, data!!["monday17"] as String, 0)
                    changebutton(monday18, data!!["monday18"] as String, 0)
                    changebutton(monday19, data!!["monday19"] as String, 0)
                    changebutton(monday20, data!!["monday20"] as String, 0)

                    changebutton(tuesday9, data!!["tuesday9"] as String, 1)
                    changebutton(tuesday10, data!!["tuesday10"] as String, 1)
                    changebutton(tuesday11, data!!["tuesday11"] as String, 1)
                    changebutton(tuesday12, data!!["tuesday12"] as String, 1)
                    changebutton(tuesday13, data!!["tuesday13"] as String, 1)
                    changebutton(tuesday14, data!!["tuesday14"] as String, 1)
                    changebutton(tuesday15, data!!["tuesday15"] as String, 1)
                    changebutton(tuesday16, data!!["tuesday16"] as String, 1)
                    changebutton(tuesday17, data!!["tuesday17"] as String, 1)
                    changebutton(tuesday18, data!!["tuesday18"] as String, 1)
                    changebutton(tuesday19, data!!["tuesday19"] as String, 1)
                    changebutton(tuesday20, data!!["tuesday20"] as String, 1)

                    changebutton(wednesday9, data!!["wednesday9"] as String, 2)
                    changebutton(wednesday10, data!!["wednesday10"] as String, 2)
                    changebutton(wednesday11, data!!["wednesday11"] as String, 2)
                    changebutton(wednesday12, data!!["wednesday12"] as String, 2)
                    changebutton(wednesday13, data!!["wednesday13"] as String, 2)
                    changebutton(wednesday14, data!!["wednesday14"] as String, 2)
                    changebutton(wednesday15, data!!["wednesday15"] as String, 2)
                    changebutton(wednesday16, data!!["wednesday16"] as String, 2)
                    changebutton(wednesday17, data!!["wednesday17"] as String, 2)
                    changebutton(wednesday18, data!!["wednesday18"] as String, 2)
                    changebutton(wednesday19, data!!["wednesday19"] as String, 2)
                    changebutton(wednesday20, data!!["wednesday20"] as String, 2)

                    changebutton(thursday9, data!!["thursday9"] as String, 3)
                    changebutton(thursday10, data!!["thursday10"] as String, 3)
                    changebutton(thursday11, data!!["thursday11"] as String, 3)
                    changebutton(thursday12, data!!["thursday12"] as String, 3)
                    changebutton(thursday13, data!!["thursday13"] as String, 3)
                    changebutton(thursday14, data!!["thursday14"] as String, 3)
                    changebutton(thursday15, data!!["thursday15"] as String, 3)
                    changebutton(thursday16, data!!["thursday16"] as String, 3)
                    changebutton(thursday17, data!!["thursday17"] as String, 3)
                    changebutton(thursday18, data!!["thursday18"] as String, 3)
                    changebutton(thursday19, data!!["thursday19"] as String, 3)
                    changebutton(thursday20, data!!["thursday20"] as String, 3)

                    changebutton(friday9, data!!["friday9"] as String, 4)
                    changebutton(friday10, data!!["friday10"] as String, 4)
                    changebutton(friday11, data!!["friday11"] as String, 4)
                    changebutton(friday12, data!!["friday12"] as String, 4)
                    changebutton(friday13, data!!["friday13"] as String, 4)
                    changebutton(friday14, data!!["friday14"] as String, 4)
                    changebutton(friday15, data!!["friday15"] as String, 4)
                    changebutton(friday16, data!!["friday16"] as String, 4)
                    changebutton(friday17, data!!["friday17"] as String, 4)
                    changebutton(friday18, data!!["friday18"] as String, 4)
                    changebutton(friday19, data!!["friday19"] as String, 4)
                    changebutton(friday20, data!!["friday20"] as String, 4)
                }
            }
            .addOnFailureListener { exception -> // 데이터 불러오기 실패
                Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }

        // user 콜렉션에 뭔가 있으면 (예약 내역 있으면) 불러오기. 함수 작동하는거 확인되면 그냥 데이터를 지워버리자
        bookDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data

                    for (i in 1 .. 20) {
                        val existcheck = data?.get("숙소"+i.toString()+"이름") as String?
                        if (existcheck != null) {
                            val name = data!![("숙소"+i.toString()+"이름")] as String
                            val daytime = data!!["숙소"+i.toString()+"예약날짜"] as String
                            val check = data!!["숙소"+i.toString()+"체크"] as String
                            var previous_name = "이게 보이면 안되는데"

                            fun viewautoDialog() {
                                if (previous_name != name) {
                                    val dialog = Dialog(this)
                                    dialog.setContentView(R.layout.dialog_autoschedule)

                                    val autoschedule_nametime = dialog.findViewById<TextView>(R.id.autoschedule_nametime)
                                    val autoschedule_previous = dialog.findViewById<TextView>(R.id.autoschedule_previous)
                                    val autoschedule_confirm = dialog.findViewById<Button>(R.id.autoschedule_confirm)
                                    val autoschedule_cancel = dialog.findViewById<Button>(R.id.autoschedule_cancel)

                                    // 다이얼로그 내용 바꾸기
                                    fun changetext(daytime: String, autoname: String) {
                                        var tempday = "무슨요일"
                                        var temptime = "몇시"

                                        // textView 내용 두 개 바꾸는 함수
                                        fun present_previous(daytime: String, autoname: String) {

                                            if (daytime.contains("monday")) { tempday = "월요일 " }
                                            else if (daytime.contains("tuesday")) { tempday = "화요일 " }
                                            else if (daytime.contains("wednesday")) { tempday = "수요일 " }
                                            else if (daytime.contains("thursday")) { tempday = "목요일 " }
                                            else if (daytime.contains("friday")) { tempday = "금요일 " }

                                            if (daytime.contains("9")) { temptime = "9시, " }
                                            else if (daytime.contains("10")) { temptime = "10시, " }
                                            else if (daytime.contains("11")) { temptime = "11시, " }
                                            else if (daytime.contains("12")) { temptime = "12시, " }
                                            else if (daytime.contains("13")) { temptime = "13시, " }
                                            else if (daytime.contains("14")) { temptime = "14시, " }
                                            else if (daytime.contains("15")) { temptime = "15시, " }
                                            else if (daytime.contains("16")) { temptime = "16시, " }
                                            else if (daytime.contains("17")) { temptime = "17시, " }
                                            else if (daytime.contains("18")) { temptime = "18시, " }
                                            else if (daytime.contains("20")) { temptime = "20시, " }

                                            if (daytime.contains("19")) { temptime = "19시, " }

                                            autoschedule_nametime.text = tempday + temptime + autoname

                                            if (previous_name == " ") { autoschedule_previous.text = "이 시간의 일정이 없습니다." }
                                            else { autoschedule_previous.text = "이 시간의 일정: " + previous_name }
                                        }
                                        present_previous(daytime, autoname)

                                        // 확인 버튼
                                        autoschedule_confirm.setOnClickListener {
                                            userDocument.update(daytime, autoname)
                                            if (daytime == "monday9") {changebutton(monday9, autoname, 0)}
                                            else if (daytime == "monday10") {changebutton(monday10, autoname, 0)}
                                            else if (daytime == "monday11") {changebutton(monday11, autoname, 0)}
                                            else if (daytime == "monday12") {changebutton(monday12, autoname, 0)}
                                            else if (daytime == "monday13") {changebutton(monday13, autoname, 0)}
                                            else if (daytime == "monday14") {changebutton(monday14, autoname, 0)}
                                            else if (daytime == "monday15") {changebutton(monday15, autoname, 0)}
                                            else if (daytime == "monday16") {changebutton(monday16, autoname, 0)}
                                            else if (daytime == "monday17") {changebutton(monday17, autoname, 0)}
                                            else if (daytime == "monday18") {changebutton(monday18, autoname, 0)}
                                            else if (daytime == "monday19") {changebutton(monday19, autoname, 0)}
                                            else if (daytime == "monday20") {changebutton(monday20, autoname, 0)}

                                            else if (daytime == "tuesday9") {changebutton(tuesday9, autoname, 1)}
                                            else if (daytime == "tuesday10") {changebutton(tuesday10, autoname, 1)}
                                            else if (daytime == "tuesday11") {changebutton(tuesday11, autoname, 1)}
                                            else if (daytime == "tuesday12") {changebutton(tuesday12, autoname, 1)}
                                            else if (daytime == "tuesday13") {changebutton(tuesday13, autoname, 1)}
                                            else if (daytime == "tuesday14") {changebutton(tuesday14, autoname, 1)}
                                            else if (daytime == "tuesday15") {changebutton(tuesday15, autoname, 1)}
                                            else if (daytime == "tuesday16") {changebutton(tuesday16, autoname, 1)}
                                            else if (daytime == "tuesday17") {changebutton(tuesday17, autoname, 1)}
                                            else if (daytime == "tuesday18") {changebutton(tuesday18, autoname, 1)}
                                            else if (daytime == "tuesday19") {changebutton(tuesday19, autoname, 1)}
                                            else if (daytime == "tuesday20") {changebutton(tuesday20, autoname, 1)}

                                            else if (daytime == "wednesday9") {changebutton(wednesday9, autoname, 2)}
                                            else if (daytime == "wednesday10") {changebutton(wednesday10, autoname, 2)}
                                            else if (daytime == "wednesday11") {changebutton(wednesday11, autoname, 2)}
                                            else if (daytime == "wednesday12") {changebutton(wednesday12, autoname, 2)}
                                            else if (daytime == "wednesday13") {changebutton(wednesday13, autoname, 2)}
                                            else if (daytime == "wednesday14") {changebutton(wednesday14, autoname, 2)}
                                            else if (daytime == "wednesday15") {changebutton(wednesday15, autoname, 2)}
                                            else if (daytime == "wednesday16") {changebutton(wednesday16, autoname, 2)}
                                            else if (daytime == "wednesday17") {changebutton(wednesday17, autoname, 2)}
                                            else if (daytime == "wednesday18") {changebutton(wednesday18, autoname, 2)}
                                            else if (daytime == "wednesday19") {changebutton(wednesday19, autoname, 2)}
                                            else if (daytime == "wednesday20") {changebutton(wednesday20, autoname, 2)}

                                            else if (daytime == "thursday9") {changebutton(thursday9, autoname, 3)}
                                            else if (daytime == "thursday10") {changebutton(thursday10, autoname, 3)}
                                            else if (daytime == "thursday11") {changebutton(thursday11, autoname, 3)}
                                            else if (daytime == "thursday12") {changebutton(thursday12, autoname, 3)}
                                            else if (daytime == "thursday13") {changebutton(thursday13, autoname, 3)}
                                            else if (daytime == "thursday14") {changebutton(thursday14, autoname, 3)}
                                            else if (daytime == "thursday15") {changebutton(thursday15, autoname, 3)}
                                            else if (daytime == "thursday16") {changebutton(thursday16, autoname, 3)}
                                            else if (daytime == "thursday17") {changebutton(thursday17, autoname, 3)}
                                            else if (daytime == "thursday18") {changebutton(thursday18, autoname, 3)}
                                            else if (daytime == "thursday19") {changebutton(thursday19, autoname, 3)}
                                            else if (daytime == "thursday20") {changebutton(thursday20, autoname, 3)}

                                            else if (daytime == "friday9") {changebutton(friday9, autoname, 4)}
                                            else if (daytime == "friday10") {changebutton(friday10, autoname, 4)}
                                            else if (daytime == "friday11") {changebutton(friday11, autoname, 4)}
                                            else if (daytime == "friday12") {changebutton(friday12, autoname, 4)}
                                            else if (daytime == "friday13") {changebutton(friday13, autoname, 4)}
                                            else if (daytime == "friday14") {changebutton(friday14, autoname, 4)}
                                            else if (daytime == "friday15") {changebutton(friday15, autoname, 4)}
                                            else if (daytime == "friday16") {changebutton(friday16, autoname, 4)}
                                            else if (daytime == "friday17") {changebutton(friday17, autoname, 4)}
                                            else if (daytime == "friday18") {changebutton(friday18, autoname, 4)}
                                            else if (daytime == "friday19") {changebutton(friday19, autoname, 4)}
                                            else if (daytime == "friday20") {changebutton(friday20, autoname, 4)}

                                            dialog.dismiss()
                                        }
                                        // 취소버튼
                                        autoschedule_cancel.setOnClickListener { dialog.dismiss() }
                                    }
                                    changetext(daytime, name)

                                    dialog.show()
                                }
                            }

                            if (check == "0") {
                                userDocument.get()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            val documentSnapshot = task.result
                                            if (documentSnapshot != null && documentSnapshot.exists()) {
                                                val data1 = documentSnapshot.data
                                                previous_name = data1!![daytime] as String
                                                viewautoDialog()
                                            }
                                        }
                                        else { Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show() }
                                    }
                                bookDocument.update("숙소"+i.toString()+"체크", "1")
                            }
                        }
                        else { break }
                    }
                }
            }

        // 시간표 각 칸별로 일정 수정 다이얼로그
        fun editplan(button: Button, day: String, time: String, tempName: String) {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_editschedule)

            val previous_day = dialog.findViewById<TextView>(R.id.previous_day)
            val reschedulenameid = dialog.findViewById<EditText>(R.id.reschedule_name)
            reschedulenameid.hint = button.text
            var reschedulename = " "
            val rescheduleconfirmbutton = dialog.findViewById<Button>(R.id.reschedule_confirm)
            val deleteschedulebutton = dialog.findViewById<Button>(R.id.delete_schedule)
            val cancelreschedule = dialog.findViewById<Button>(R.id.cancel_reschedule)

            var color = 5
            if (tempName.contains("monday")) { color = 0 }
            else if (tempName.contains("tuesday")) { color = 1 }
            else if (tempName.contains("wednesday")) { color = 2 }
            else if (tempName.contains("thursday")) { color = 3 }
            else if (tempName.contains("friday")) { color = 4 }

            previous_day.text = day + ", " + time

            // 이건 일정 이름 받는 함수
            reschedulenameid.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    reschedulename = s.toString()
                }
                override fun afterTextChanged(s: Editable) { }
            })

            rescheduleconfirmbutton.setOnClickListener  {
                if (reschedulename == " ") { initplan(button) }
                else if (reschedulename == "") { dialog.dismiss() }
                else { changebutton(button, reschedulename, color) }

                userDocument.update(tempName, reschedulename)

                dialog.dismiss()
            }

            deleteschedulebutton.setOnClickListener { 
                initplan(button)
                userDocument.update(tempName, " ")
                dialog.dismiss()
            }

            cancelreschedule.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        // 일정 추가 버튼 다이얼로그
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
            val timeselect_21 = dialog.findViewById<CheckBox>(R.id.timeselect_21)

            var checkedCount = 0 // 선택된 체크박스 개수, 2개 이상 선택 못하도록 막을 거...
            var dayCount = 0 // 요일 체크박스 개수, 1 이상이어야 픽스됨...

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
                if (isChecked) { dayselect[0] = 1;  dayCount++ }
                else { dayselect[0] = 0;    dayCount-- }
            }
            dayselect_tue.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[1] = 1;  dayCount++ }
                else { dayselect[1] = 0;    dayCount-- }
            }
            dayselect_wed.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[2] = 1;  dayCount++ }
                else { dayselect[2] = 0;    dayCount-- }
            }
            dayselect_thu.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[3] = 1;  dayCount++ }
                else { dayselect[3] = 0;    dayCount-- }
            }
            dayselect_fri.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) { dayselect[4] = 1;  dayCount++ }
                else { dayselect[4] = 0;    dayCount-- }
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

            fixbutton.setOnClickListener {
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

                if ((schedulename != "" || schedulename != " ") && dayCount > 0 && starttime != -1 && finishtime != -1) {
                    starttime += 9
                    finishtime += 8
                    // 요일 먼저 for문으로 돌리면서 시간 지정하기

                    for (i in 0..4) {
                        if (dayselect[i] == 1)  {
                            selectedday = day[i]

                            for (i in 0..(finishtime-starttime)) {
                                val tempname = selectedday + (i+starttime).toString()

                                if (tempname == "monday9") { changebutton(monday9, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday10") { changebutton(monday10, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday11") { changebutton(monday11, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday12") { changebutton(monday12, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday13") { changebutton(monday13, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday14") { changebutton(monday14, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday15") { changebutton(monday15, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday16") { changebutton(monday16, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday17") { changebutton(monday17, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday18") { changebutton(monday18, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday19") { changebutton(monday19, schedulename, 0); userDocument.update(tempname, schedulename) }
                                else if (tempname == "monday20") { changebutton(monday20, schedulename, 0); userDocument.update(tempname, schedulename) }

                                else if (tempname == "tuesday9") { changebutton(tuesday9, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday10") { changebutton(tuesday10, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday11") { changebutton(tuesday11, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday12") { changebutton(tuesday12, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday13") { changebutton(tuesday13, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday14") { changebutton(tuesday14, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday15") { changebutton(tuesday15, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday16") { changebutton(tuesday16, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday17") { changebutton(tuesday17, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday18") { changebutton(tuesday18, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday19") { changebutton(tuesday19, schedulename, 1); userDocument.update(tempname, schedulename) }
                                else if (tempname == "tuesday20") { changebutton(tuesday20, schedulename, 1); userDocument.update(tempname, schedulename) }

                                else if (tempname == "wednesday9") { changebutton(wednesday9, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday10") { changebutton(wednesday10, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday11") { changebutton(wednesday11, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday12") { changebutton(wednesday12, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday13") { changebutton(wednesday13, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday14") { changebutton(wednesday14, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday15") { changebutton(wednesday15, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday16") { changebutton(wednesday16, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday17") { changebutton(wednesday17, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday18") { changebutton(wednesday18, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday19") { changebutton(wednesday19, schedulename, 2); userDocument.update(tempname, schedulename) }
                                else if (tempname == "wednesday20") { changebutton(wednesday20, schedulename, 2); userDocument.update(tempname, schedulename) }

                                else if (tempname == "thursday9") { changebutton(thursday9, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday10") { changebutton(thursday10, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday11") { changebutton(thursday11, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday12") { changebutton(thursday12, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday13") { changebutton(thursday13, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday14") { changebutton(thursday14, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday15") { changebutton(thursday15, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday16") { changebutton(thursday16, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday17") { changebutton(thursday17, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday18") { changebutton(thursday18, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday19") { changebutton(thursday19, schedulename, 3); userDocument.update(tempname, schedulename) }
                                else if (tempname == "thursday20") { changebutton(thursday20, schedulename, 3); userDocument.update(tempname, schedulename) }

                                else if (tempname == "friday9") { changebutton(friday9, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday10") { changebutton(friday10, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday11") { changebutton(friday11, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday12") { changebutton(friday12, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday13") { changebutton(friday13, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday14") { changebutton(friday14, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday15") { changebutton(friday15, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday16") { changebutton(friday16, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday17") { changebutton(friday17, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday18") { changebutton(friday18, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday19") { changebutton(friday19, schedulename, 4); userDocument.update(tempname, schedulename) }
                                else if (tempname == "friday20") { changebutton(friday20, schedulename, 4); userDocument.update(tempname, schedulename) }

                            }
                        }
                    }
                    dialog.dismiss()
                }
                else {
                    if (schedulename == "" || schedulename == " ")
                        Toast.makeText(this, "일정 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                    else if (dayCount == 0)
                        Toast.makeText(this, "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                    else if (checkedCount != 2)
                        Toast.makeText(this, "시작과 끝 시간을 모두 선택해 주세요.", Toast.LENGTH_SHORT).show()
                    else {
                        val message1 = "이름 " + schedulename + "요일 " + selectedday + "시작 " + starttime + ", " + finishtime + "카운트 " + checkedCount
                        Toast.makeText(this, message1, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            cancelbutton.setOnClickListener {dialog.dismiss()}

            dialog.show()
        }

        // 예약 내역 보기 액티비티로 이동
        viewBooked.setOnClickListener {
            var intent = Intent(this, ViewBookedActivity::class.java)
            startActivity(intent)
        }

        // 장바구니 보기 액티비티로 이동
        viewCart.setOnClickListener { Toast.makeText(this, "아직 연동이 안 됐어요.", Toast.LENGTH_SHORT).show() }

        // 일정 전체 삭제 버튼 다이얼로그
        deleteAllSchedule.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_deleteallschedules)

            val deleteall_confirm = dialog.findViewById<Button>(R.id.deleteall_confirm)
            val deleteall_cancel = dialog.findViewById<Button>(R.id.deleteall_cancel)

            deleteall_confirm.setOnClickListener  {
                initplan(monday9); initplan(monday10); initplan(monday11); initplan(monday12); initplan(monday13); initplan(monday14); initplan(monday15); initplan(monday16); initplan(monday17); initplan(monday18); initplan(monday19); initplan(monday20)
                initplan(tuesday9); initplan(tuesday10); initplan(tuesday11); initplan(tuesday12); initplan(tuesday13); initplan(tuesday14); initplan(tuesday15); initplan(tuesday16); initplan(tuesday17); initplan(tuesday18); initplan(tuesday19); initplan(tuesday20)
                initplan(wednesday9); initplan(wednesday10); initplan(wednesday11); initplan(wednesday12); initplan(wednesday13); initplan(wednesday14); initplan(wednesday15); initplan(wednesday16); initplan(wednesday17); initplan(wednesday18); initplan(wednesday19); initplan(wednesday20)
                initplan(thursday9); initplan(thursday10); initplan(thursday11); initplan(thursday12); initplan(thursday13); initplan(thursday14); initplan(thursday15); initplan(thursday16); initplan(thursday17); initplan(thursday18); initplan(thursday19); initplan(thursday20)
                initplan(friday9); initplan(friday10); initplan(friday11); initplan(friday12); initplan(friday13); initplan(friday14); initplan(friday15); initplan(friday16); initplan(friday17); initplan(friday18); initplan(friday19); initplan(friday20)

                val updates = hashMapOf<String, Any>(
                    "monday9" to " ", "monday10" to " ", "monday11" to " ", "monday12" to " ", "monday13" to " ", "monday14" to " ", "monday15" to " ", "monday16" to " ", "monday17" to " ", "monday18" to " ", "monday19" to " ", "monday20" to " ",
                    "tuesday9" to " ", "tuesday10" to " ", "tuesday11" to " ", "tuesday12" to " ", "tuesday13" to " ", "tuesday14" to " ", "tuesday15" to " ", "tuesday16" to " ", "tuesday17" to " ", "tuesday18" to " ", "tuesday19" to " ", "tuesday20" to " ",
                    "wednesday9" to " ", "wednesday10" to " ", "wednesday11" to " ", "wednesday12" to " ", "wednesday13" to " ", "wednesday14" to " ", "wednesday15" to " ", "wednesday16" to " ", "wednesday17" to " ", "wednesday18" to " ", "wednesday19" to " ", "wednesday20" to " ",
                    "thursday9" to " ", "thursday10" to " ", "thursday11" to " ", "thursday12" to " ", "thursday13" to " ", "thursday14" to " ", "thursday15" to " ", "thursday16" to " ", "thursday17" to " ", "thursday18" to " ", "thursday19" to " ", "thursday20" to " ",
                    "friday9" to " ", "friday10" to " ", "friday11" to " ", "friday12" to " ", "friday13" to " ", "friday14" to " ", "friday15" to " ", "friday16" to " ", "friday17" to " ", "friday18" to " ", "friday19" to " ", "friday20" to " "
                )
                userDocument.update(updates)

                Toast.makeText(this, "모든 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }
            deleteall_cancel.setOnClickListener { dialog.dismiss() }

            dialog.show()
        }

        // 각 버튼 별 시간표 일정 수정/삭제 버튼
        monday9.setOnClickListener  {editplan(monday9, "월요일", "9시~10시", "monday9")}
        monday10.setOnClickListener  {editplan(monday10, "월요일", "10시~11시", "monday10")}
        monday11.setOnClickListener  {editplan(monday11, "월요일", "11시~12시", "monday11")}
        monday12.setOnClickListener  {editplan(monday12, "월요일", "12시~13시", "monday12")}
        monday13.setOnClickListener  {editplan(monday13, "월요일", "13시~14시", "monday13")}
        monday14.setOnClickListener  {editplan(monday14, "월요일", "14시~15시", "monday14")}
        monday15.setOnClickListener  {editplan(monday15, "월요일", "15시~16시", "monday15")}
        monday16.setOnClickListener  {editplan(monday16, "월요일", "16시~17시", "monday16")}
        monday17.setOnClickListener  {editplan(monday17, "월요일", "17시~18시", "monday17")}
        monday18.setOnClickListener  {editplan(monday18, "월요일", "18시~19시", "monday18")}
        monday19.setOnClickListener  {editplan(monday19, "월요일", "19시~20시", "monday19")}
        monday20.setOnClickListener  {editplan(monday20, "월요일", "20시~21시", "monday20")}

        tuesday9.setOnClickListener  {editplan(tuesday9, "화요일", "9시~10시", "tuesday9")}
        tuesday10.setOnClickListener  {editplan(tuesday10, "화요일", "10시~11시", "tuesday10")}
        tuesday11.setOnClickListener  {editplan(tuesday11, "화요일", "11시~12시", "tuesday11")}
        tuesday12.setOnClickListener  {editplan(tuesday12, "화요일", "12시~13시", "tuesday12")}
        tuesday13.setOnClickListener  {editplan(tuesday13, "화요일", "13시~14시", "tuesday13")}
        tuesday14.setOnClickListener  {editplan(tuesday14, "화요일", "14시~15시", "tuesday14")}
        tuesday15.setOnClickListener  {editplan(tuesday15, "화요일", "15시~16시", "tuesday15")}
        tuesday16.setOnClickListener  {editplan(tuesday16, "화요일", "16시~17시", "tuesday16")}
        tuesday17.setOnClickListener  {editplan(tuesday17, "화요일", "17시~18시", "tuesday17")}
        tuesday18.setOnClickListener  {editplan(tuesday18, "화요일", "18시~19시", "tuesday18")}
        tuesday19.setOnClickListener  {editplan(tuesday19, "화요일", "19시~20시", "tuesday19")}
        tuesday20.setOnClickListener  {editplan(tuesday20, "화요일", "20시~21시", "tuesday20")}

        wednesday9.setOnClickListener  {editplan(wednesday9, "수요일", "9시~10시", "wednesday9")}
        wednesday10.setOnClickListener  {editplan(wednesday10, "수요일", "10시~11시", "wednesday10")}
        wednesday11.setOnClickListener  {editplan(wednesday11, "수요일", "11시~12시", "wednesday11")}
        wednesday12.setOnClickListener  {editplan(wednesday12, "수요일", "12시~13시", "wednesday12")}
        wednesday13.setOnClickListener  {editplan(wednesday13, "수요일", "13시~14시", "wednesday13")}
        wednesday14.setOnClickListener  {editplan(wednesday14, "수요일", "14시~15시", "wednesday14")}
        wednesday15.setOnClickListener  {editplan(wednesday15, "수요일", "15시~16시", "wednesday15")}
        wednesday16.setOnClickListener  {editplan(wednesday16, "수요일", "16시~17시", "wednesday16")}
        wednesday17.setOnClickListener  {editplan(wednesday17, "수요일", "17시~18시", "wednesday17")}
        wednesday18.setOnClickListener  {editplan(wednesday18, "수요일", "18시~19시", "wednesday18")}
        wednesday19.setOnClickListener  {editplan(wednesday19, "수요일", "19시~20시", "wednesday19")}
        wednesday20.setOnClickListener  {editplan(wednesday20, "수요일", "20시~21시", "wednesday20")}

        thursday9.setOnClickListener  {editplan(thursday9, "목요일", "9시~10시", "thursday9")}
        thursday10.setOnClickListener  {editplan(thursday10, "목요일", "10시~11시", "thursday10")}
        thursday11.setOnClickListener  {editplan(thursday11, "목요일", "11시~12시", "thursday11")}
        thursday12.setOnClickListener  {editplan(thursday12, "목요일", "12시~13시", "thursday12")}
        thursday13.setOnClickListener  {editplan(thursday13, "목요일", "13시~14시", "thursday13")}
        thursday14.setOnClickListener  {editplan(thursday14, "목요일", "14시~15시", "thursday14")}
        thursday15.setOnClickListener  {editplan(thursday15, "목요일", "15시~16시", "thursday15")}
        thursday16.setOnClickListener  {editplan(thursday16, "목요일", "16시~17시", "thursday16")}
        thursday17.setOnClickListener  {editplan(thursday17, "목요일", "17시~18시", "thursday17")}
        thursday18.setOnClickListener  {editplan(thursday18, "목요일", "18시~19시", "thursday18")}
        thursday19.setOnClickListener  {editplan(thursday19, "목요일", "19시~20시", "thursday19")}
        thursday20.setOnClickListener  {editplan(thursday20, "목요일", "20시~21시", "thursday20")}

        friday9.setOnClickListener  {editplan(friday9, "금요일", "9시~10시", "friday9")}
        friday10.setOnClickListener  {editplan(friday10, "금요일", "10시~11시", "friday10")}
        friday11.setOnClickListener  {editplan(friday11, "금요일", "11시~12시", "friday11")}
        friday12.setOnClickListener  {editplan(friday12, "금요일", "12시~13시", "friday12")}
        friday13.setOnClickListener  {editplan(friday13, "금요일", "13시~14시", "friday13")}
        friday14.setOnClickListener  {editplan(friday14, "금요일", "14시~15시", "friday14")}
        friday15.setOnClickListener  {editplan(friday15, "금요일", "15시~16시", "friday15")}
        friday16.setOnClickListener  {editplan(friday16, "금요일", "16시~17시", "friday16")}
        friday17.setOnClickListener  {editplan(friday17, "금요일", "17시~18시", "friday17")}
        friday18.setOnClickListener  {editplan(friday18, "금요일", "18시~19시", "friday18")}
        friday19.setOnClickListener  {editplan(friday19, "금요일", "19시~20시", "friday19")}
        friday20.setOnClickListener  {editplan(friday20, "금요일", "20시~21시", "friday20")}
        

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