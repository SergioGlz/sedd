package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Employee(
    val employeeId: String,
    val displayName: String,
) : Serializable

