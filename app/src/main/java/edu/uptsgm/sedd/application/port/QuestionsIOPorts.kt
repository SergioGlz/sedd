package edu.uptsgm.sedd.application.port

import edu.uptsgm.sedd.application.model.Question

interface AnswerQuestionInputPort {

    data class Answer(
        val grade: Int? = null,
        val body: String? = null,
        val comments: String? = null
    )

    suspend fun answerQuestion(idQuestion: String, answer: Answer): Result<Question>
}

interface AnswerQuestionOutputPort {
    suspend fun answerQuestion(idQuestion: String, answer: AnswerQuestionInputPort.Answer): Result<Question>
}

interface GetQuestionsInputPort {
    suspend fun <T> getQuestions(input: T): Result<List<Question>>
}

interface GetQuestionsOutputPort {
    suspend fun getQuestionsFromEvaluation(evaluationId: String): Result<List<Question>>
    suspend fun getQuestionsFromSkill(skillId: String): Result<List<Question>>
}

interface QuestionsCRUDInputPort: GetQuestionsInputPort {
    suspend fun createQuestion(question: Question): Result<Nothing>
    suspend fun updateQuestion(question: Question): Result<Nothing>
    suspend fun deleteQuestion(idQuestion: String): Result<Nothing>
}

interface QuestionsCRUDOutputPort: GetQuestionsOutputPort {
    suspend fun createQuestion(question: Question): Result<Nothing>
    suspend fun updateQuestion(question: Question): Result<Nothing>
    suspend fun deleteQuestion(idQuestion: String): Result<Nothing>
}