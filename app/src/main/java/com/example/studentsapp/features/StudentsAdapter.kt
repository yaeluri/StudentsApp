package com.example.studentsapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentsAdapter(
    private val onRowClick: (Student) -> Unit,
    private val onCheckedToggle: (Student) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    private var students: List<Student> = emptyList()

    fun submitList(list: List<Student>) {
        students = list
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStudent: ImageView = itemView.findViewById(R.id.imgStudent)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val chkChecked: CheckBox = itemView.findViewById(R.id.chkChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.imgStudent.setImageResource(R.drawable.person)
        holder.txtName.text = student.name
        holder.txtId.text = student.id

        // חשוב: למנוע "קפיצות" בגלל recycle
        holder.chkChecked.setOnCheckedChangeListener(null)
        holder.chkChecked.isChecked = student.isChecked

        holder.itemView.setOnClickListener { onRowClick(student) }

        holder.chkChecked.setOnCheckedChangeListener { _, _ ->
            onCheckedToggle(student)
        }
    }

    override fun getItemCount(): Int = students.size
}
