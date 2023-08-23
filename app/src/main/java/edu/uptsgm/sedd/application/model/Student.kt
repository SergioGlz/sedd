package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Student(
    val studentId: String,
    val displayName: String,
    val mayor: String,
    var groups: List<Group>? = null
) : Serializable

