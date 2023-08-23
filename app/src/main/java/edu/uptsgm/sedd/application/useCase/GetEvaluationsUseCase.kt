package edu.uptsgm.sedd.application.useCase

import edu.uptsgm.sedd.application.model.Employee
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.application.port.GetEvaluationsInputPort
import edu.uptsgm.sedd.application.port.GetEvaluationsOutputPort
import edu.uptsgm.sedd.interfaceAdapter.operators.Result
import java.io.IOException

class GetEvaluationsUseCase(private val getEvaluationsOutputPort: GetEvaluationsOutputPort): GetEvaluationsInputPort {

    override suspend fun <T> getEvaluations(input: T): Result<List<Evaluation>> = when (input) {
        is Student -> getEvaluationsOutputPort.getStudentEvaluations(input.studentId)
        is Employee -> getEvaluationsOutputPort.getProfessorEvaluations(input.employeeId)
        else -> Result.Error(IOException("Input value is not an Employee or a Student."))
    }

}