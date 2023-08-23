package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Question(
    val idQuestions: String?,
    val questionBody: String,
    val skillsAssociated: List<Skill>,
    val type: QuestionType = QuestionType.RANGE,
    var answerGrade: Int? = null,
    var answerBody: String? = null,
    var comments: String? = null,
) : Serializable

enum class QuestionType() {
    RANGE(),
    YES_NO(),
    OPEN(),
    COMMENT()
}
