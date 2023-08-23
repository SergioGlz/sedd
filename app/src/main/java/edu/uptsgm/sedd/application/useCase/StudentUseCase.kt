package edu.uptsgm.sedd.application.useCase

import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.port.StudentInputPort
import edu.uptsgm.sedd.application.port.StudentOutputPort
import edu.uptsgm.sedd.interfaceAdapter.operators.Result

class StudentUseCase(private val studentOutputPort: StudentOutputPort): StudentInputPort {

    override suspend fun getStudent(userSesion: UserSesion): Result<Student> {
        val student = studentOutputPort.getStudentInfo(userSesion.userId)
        val groups = studentOutputPort.getStudentGroups(userSesion.userId)
        if (student is Result.Success && groups is Result.Success) {
            student.data.groups = groups.data.toList()
        }
        return student
    }

}