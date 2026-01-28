package com.example.studentsapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.model.StudentsRepository
import com.example.studentsapp.Nav

class StudentsListActivity : AppCompatActivity() {

    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        StudentsRepository.seedIfEmpty()

        adapter = StudentsAdapter(
            onRowClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra(Nav.EXTRA_STUDENT_ID, student.id)
                startActivity(intent)
            }
            ,
            onCheckedToggle = { student ->
                StudentsRepository.toggleChecked(student.id)
                adapter.submitList(StudentsRepository.getAll().toList())
            }
        )

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val fabAdd = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(StudentsRepository.getAll().toList())
    }
}