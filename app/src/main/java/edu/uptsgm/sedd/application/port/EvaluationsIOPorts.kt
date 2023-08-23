package edu.uptsgm.sedd.application.port

import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.interfaceAdapter.operators.Result

interface GetEvaluationsInputPort {
    suspend fun <T> getEvaluations(input: T): Result<List<Evaluation>>
}

interface GetEvaluationsOutputPort {
    suspend fun getStudentEvaluations(studentId: String): Result<List<Evaluation>>
    suspend fun getProfessorEvaluations(professorId: String): Result<List<Evaluation>>
}

interface EvaluationsCRUDInputPort: GetEvaluationsInputPort {
    suspend fun createEvaluation(evaluation: Evaluation): Result<Nothing>
    suspend fun updateEvaluation(evaluation: Evaluation): Result<Nothing>
    suspend fun deleteEvaluation(idEvaluation: String): Result<Nothing>
}

interface EvaluationsCRUDOutputPort: GetEvaluationsOutputPort {
    suspend fun createEvaluation(evaluation: Evaluation): Result<Nothing>
    suspend fun updateEvaluation(evaluation: Evaluation): Result<Nothing>
    suspend fun deleteEvaluation(idEvaluation: String): Result<Nothing>
}