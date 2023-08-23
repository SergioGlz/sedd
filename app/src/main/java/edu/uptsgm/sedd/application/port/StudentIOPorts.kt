package edu.uptsgm.sedd.application.port

import edu.uptsgm.sedd.application.model.Group
import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.interfaceAdapter.operators.Result

interface StudentInputPort {
    suspend fun getStudent(userSesion: UserSesion): Result<Student>
}

interface StudentOutputPort {
    suspend fun getStudentInfo(studentId: String): Result<Student>
    suspend fun getStudentGroups(studentId: String): Result<List<Group>>
}