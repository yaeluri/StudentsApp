package com.example.studentsapp.model

object StudentsRepository {
    private val students = mutableListOf<Student>()

    fun getAll(): List<Student> = students

    fun add(student: Student) {
        students.add(student)
    }

    fun getById(studentId: String): Student? {
        return students.firstOrNull { it.id == studentId }
    }

    fun update(originalId: String, updated: Student): Boolean {
        val index = students.indexOfFirst { it.id == originalId }
        if (index == -1) return false
        students[index] = updated
        return true
    }

    fun deleteById(studentId: String): Boolean {
        return students.removeIf { it.id == studentId }
    }

    fun toggleChecked(studentId: String) {
        val s = getById(studentId) ?: return
        s.isChecked = !s.isChecked
    }

    fun seedIfEmpty() {
        if (students.isNotEmpty()) return
        add(Student("Student One", "1001", "050-1111111", "Raanana", false))
        add(Student("Student Two", "1002", "050-2222222", "Tel Aviv", true))
    }
}