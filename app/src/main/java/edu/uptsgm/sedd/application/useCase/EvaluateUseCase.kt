package edu.uptsgm.sedd.application.useCase

import androidx.annotation.NonNull
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Question
import edu.uptsgm.sedd.application.model.Skill
import edu.uptsgm.sedd.application.port.AnswerQuestionInputPort
import edu.uptsgm.sedd.application.port.AnswerQuestionOutputPort
import edu.uptsgm.sedd.application.port.GetQuestionsInputPort
import edu.uptsgm.sedd.application.port.GetQuestionsOutputPort

class EvaluateUseCase(
    private val getQuestionsOutputPort: GetQuestionsOutputPort,
    private val answerQuestionOutputPort: AnswerQuestionOutputPort
) : GetQuestionsInputPort, AnswerQuestionInputPort {

    override suspend fun <T> getQuestions(@NonNull input: T): Result<List<Question>> {
        return when (input) {
            is Evaluation -> getQuestionsOutputPort.getQuestionsFromEvaluation(input.evaluationId)
            is Skill -> getQuestionsOutputPort.getQuestionsFromSkill(input.skillId!!)
            else -> Result.failure(Throwable("Input value is not an Evaluation or a Skill."))
        }
    }

    override suspend fun answerQuestion(
        idQuestion: String,
        answer: AnswerQuestionInputPort.Answer
    ): Result<Question> = answerQuestionOutputPort.answerQuestion(idQuestion, answer)

}