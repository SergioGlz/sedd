package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Group(
    val groupId: String,
    val displayName: String,
    val professor: Employee,
    val students: List<Student>? = null
) : Serializable
