package com.example.studentsapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.Nav
import com.example.studentsapp.R
import com.example.studentsapp.model.StudentsRepository

class StudentDetailsActivity : AppCompatActivity() {

    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentId = intent.getStringExtra(Nav.EXTRA_STUDENT_ID)
        if (studentId == null) {
            Toast.makeText(this, "Missing student id", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val imgStudent = findViewById<ImageView>(R.id.imgStudent)
        imgStudent.setImageResource(R.drawable.person)

        val btnEdit = findViewById<Button>(R.id.btnEdit)
        btnEdit.setOnClickListener {

            Toast.makeText(this, "Edit screen in next commit", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onResume() {
        super.onResume()

        val id = studentId ?: return
        val student = StudentsRepository.getById(id)

        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        findViewById<TextView>(R.id.txtName).text = student.name
        findViewById<TextView>(R.id.txtId).text = student.id
        findViewById<TextView>(R.id.txtPhone).text = student.phone
        findViewById<TextView>(R.id.txtAddress).text = student.address
        findViewById<CheckBox>(R.id.chkChecked).isChecked = student.isChecked

        studentId = student.id
    }
}
