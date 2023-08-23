package edu.uptsgm.sedd.application.model

data class Department(
    val departmentId: String,
    val displayName: String,
    val director: Employee,
    val professors: List<Employee>
)

data class Area(
    val areaId: String,
    val displayName: String,
    val director: Employee,
    val departments: List<Department>
)
