package com.example.capstone

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Carted(
    var name: String,
    var category: String,
    var daytime: String
)

class ViewCarted(private val dataList: List<Carted>) : RecyclerView.Adapter<ViewCarted.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carted_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int { return dataList.size }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryname: TextView = itemView.findViewById<TextView>(R.id.categoryname)
        private val cartedname: TextView = itemView.findViewById<TextView>(R.id.cartedname)
        private val daytime: TextView = itemView.findViewById<TextView>(R.id.daytime)
        private val addplan: Button = itemView.findViewById<Button>(R.id.addplan)

        fun bind(data: Carted) {

            val layoutManager = LinearLayoutManager(itemView.context)

            categoryname.text = data.category
            cartedname.text = data.name

            var presentedtext = "이게 보이면 안됨"
            var temptime = "임시용"

            if (data.daytime.contains("monday")) {presentedtext = "월요일 "}
            else if (data.daytime.contains("tuesday")) {presentedtext = "화요일 "}
            else if (data.daytime.contains("wednesday")) {presentedtext = "수요일 "}
            else if (data.daytime.contains("thursday")) {presentedtext = "목요일 "}
            else if (data.daytime.contains("friday")) {presentedtext = "금요일 "}

            if (data.daytime.contains("9")) {temptime = "9시"}
            else if (data.daytime.contains("10")) {temptime = "10시"}
            else if (data.daytime.contains("11")) {temptime = "11시"}
            else if (data.daytime.contains("12")) {temptime = "12시"}
            else if (data.daytime.contains("13")) {temptime = "13시"}
            else if (data.daytime.contains("14")) {temptime = "14시"}
            else if (data.daytime.contains("15")) {temptime = "15시"}
            else if (data.daytime.contains("16")) {temptime = "16시"}
            else if (data.daytime.contains("17")) {temptime = "17시"}
            else if (data.daytime.contains("18")) {temptime = "18시"}
            else if (data.daytime.contains("20")) {temptime = "20시"}
            if (data.daytime.contains("19")) {temptime = "19시"}

            presentedtext += temptime

            daytime.text = presentedtext

            addplan.setOnClickListener  {

                val db: FirebaseFirestore = Firebase.firestore
                val tempuser = FirebaseAuth.getInstance().currentUser
                var useremail = "email"
                if (tempuser != null) { useremail = tempuser.email.toString() }
                val userRef = db.collection("user-timetable")
                val userDocument = userRef.document(useremail)

                var previous_name = "이게 보이면 안되는데"

                fun handleUserDocument(documentSnapshot: DocumentSnapshot?) {
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        val data1 = documentSnapshot.data
                        previous_name = data1!![data.daytime] as String
                        if (previous_name == data.name) { Toast.makeText(itemView.context, "이미 시간표에 추가된 일정입니다.", Toast.LENGTH_SHORT).show() }
                        else {
                            val dialog = Dialog(itemView.context)
                            dialog.setContentView(R.layout.dialog_autoschedule)

                            val autoschedule_nametime = dialog.findViewById<TextView>(R.id.autoschedule_nametime)
                            val autoschedule_previous = dialog.findViewById<TextView>(R.id.autoschedule_previous)
                            val autoschedule_confirm = dialog.findViewById<Button>(R.id.autoschedule_confirm)
                            val autoschedule_cancel = dialog.findViewById<Button>(R.id.autoschedule_cancel)

                            fun changetext(autoname: String) {
                                fun present_previous(autoname: String) {
                                    autoschedule_nametime.text = presentedtext + ", " + autoname
                                    if (previous_name == " ") { autoschedule_previous.text = "이 시간의 일정이 없습니다." }
                                    else { autoschedule_previous.text = "이 시간의 일정: " + previous_name }
                                }
                                present_previous(autoname)

                                autoschedule_confirm.setOnClickListener {
                                    userDocument.update(data.daytime, autoname)
                                    dialog.dismiss()
                                }
                                autoschedule_cancel.setOnClickListener { dialog.dismiss() }
                            }
                            changetext(data.name)
                            dialog.show()
                        }
                    }
                }
                userDocument.get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val documentSnapshot = task.result
                            handleUserDocument(documentSnapshot)
                        } else {
                            Toast.makeText(itemView.context, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}