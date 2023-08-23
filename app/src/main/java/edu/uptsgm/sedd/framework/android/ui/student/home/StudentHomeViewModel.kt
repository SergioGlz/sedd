package edu.uptsgm.sedd.framework.android.ui.student.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.port.GetEvaluationsInputPort
import edu.uptsgm.sedd.application.port.StudentInputPort
import edu.uptsgm.sedd.interfaceAdapter.operators.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Student information : success (student details) or error message.
 */
data class GetStudentResult(
    val success: Student? = null,
    val error: Int? = null
)

/**
 * Student information : success (student details) or error message.
 */
data class EvaluationsResult(
    val success: List<Evaluation>? = null,
    val error: Int? = null
)
class StudentHomeViewModel(
    private val studentInputPort: StudentInputPort,
    private val getEvaluationsInputPort: GetEvaluationsInputPort
) : ViewModel() {

    class Factory(
        private val studentInputPort: StudentInputPort,
        private val getEvaluationsInputPort: GetEvaluationsInputPort
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StudentHomeViewModel::class.java)) {
                return StudentHomeViewModel(studentInputPort,getEvaluationsInputPort) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _studentInfo = MutableStateFlow(GetStudentResult())
    val studentInfo: StateFlow<GetStudentResult> = _studentInfo.asStateFlow()

    private val _studentEvaluations = MutableStateFlow(EvaluationsResult())
    val studentEvaluations: StateFlow<EvaluationsResult> = _studentEvaluations.asStateFlow()

    fun getStudentData(userSesion: UserSesion) {
        viewModelScope.launch(Dispatchers.IO) {
            val studentResult = studentInputPort.getStudent(userSesion)

            if (studentResult is Result.Success) {
                _studentInfo.update { GetStudentResult(success = studentResult.data) }
            } else {
                _studentInfo.update { GetStudentResult(error = R.string.get_students_failed) }
            }
        }
    }

    fun getEvaluations(student: Student) {
        viewModelScope.launch (Dispatchers.IO) {
            val evaluationsResult = getEvaluationsInputPort.getEvaluations(student)

            if (evaluationsResult is Result.Success) {
                _studentEvaluations.update { EvaluationsResult(success = evaluationsResult.data) }
            } else {
                _studentEvaluations.update { EvaluationsResult(error = R.string.get_evaluations_failed) }
            }
        }
    }

}