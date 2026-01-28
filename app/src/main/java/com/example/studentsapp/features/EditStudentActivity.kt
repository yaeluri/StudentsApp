package com.example.studentsapp.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.Nav
import com.example.studentsapp.R
import com.example.studentsapp.model.Student
import com.example.studentsapp.model.StudentsRepository

class EditStudentActivity : AppCompatActivity() {

    private var originalId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        originalId = intent.getStringExtra(Nav.EXTRA_ORIGINAL_ID)
        if (originalId == null) {
            Toast.makeText(this, "Missing student id", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val student = StudentsRepository.getById(originalId!!)
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtId = findViewById<EditText>(R.id.edtId)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtAddress = findViewById<EditText>(R.id.edtAddress)
        val chkChecked = findViewById<CheckBox>(R.id.chkChecked)

        edtName.setText(student.name)
        edtId.setText(student.id)
        edtPhone.setText(student.phone)
        edtAddress.setText(student.address)
        chkChecked.isChecked = student.isChecked

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            StudentsRepository.deleteById(originalId!!)
            finish()
        }

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val updated = Student(
                name = edtName.text.toString().trim(),
                id = edtId.text.toString().trim(),
                phone = edtPhone.text.toString().trim(),
                address = edtAddress.text.toString().trim(),
                isChecked = chkChecked.isChecked
            )

            val ok = StudentsRepository.update(originalId!!, updated)
            if (!ok) {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}
