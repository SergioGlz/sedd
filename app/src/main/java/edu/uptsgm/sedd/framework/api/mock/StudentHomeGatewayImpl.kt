package edu.uptsgm.sedd.framework.api.mock

import edu.uptsgm.sedd.application.model.Employee
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Group
import edu.uptsgm.sedd.application.model.Question
import edu.uptsgm.sedd.application.model.QuestionType
import edu.uptsgm.sedd.application.model.Recommendation
import edu.uptsgm.sedd.application.model.Skill
import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.interfaceAdapter.gateway.StudentHomeGateway
import edu.uptsgm.sedd.interfaceAdapter.operators.Result
import java.io.IOException
import java.util.UUID

class StudentHomeGatewayImpl: StudentHomeGateway {

    override suspend fun getStudentInfo(studentId: String): Result<Student> = try {
        when (studentId) {
            "a001" -> Result.Success(
                Student(studentId, "Adrian González", "Ingeniería en Sistemas Computacionales")
            )
            "a002" -> Result.Success(
                Student(studentId, "Karen Perez", "Ingeniería en Desarrollo de Software")
            )
            "a003" -> Result.Success(
                Student(studentId, "Silvino Hernandez", "Ingeniería en Tecnologías Computacionales")
            )
            else -> throw Throwable("Alumno no encontrado.")
        }
    } catch (e: Throwable) {
        Result.Error(IOException("Error obteniendo al obtener la información del estudiante.", e))
    }

    override suspend fun getStudentGroups(studentId: String): Result<List<Group>> = try {
        Result.Success(getFakeGroupsList())
    } catch (e: Throwable) {
        Result.Error(IOException("Error al obtener los cursos del estudiante.", e))
    }

    override suspend fun getStudentEvaluations(studentId: String): Result<List<Evaluation>> = try {
        val fakeEvaluations = arrayListOf<Evaluation>()
        fakeEvaluations.apply {
            when (studentId) {
                "a001" -> {
                    add(
                        Evaluation(
                            "e001", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(0), getTimeInMills(10)
                        )
                    )
                    add(
                        Evaluation(
                            "e002", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-8), getTimeInMills(2)
                        )
                    )
                    add(
                        Evaluation(
                            "e003", "Evaluación de medio termino",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-5), getTimeInMills(5)
                        )
                    )
                    add(
                        Evaluation(
                            "e004", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(-5), getTimeInMills(-1)
                        )
                    )
                    add(
                        Evaluation(
                            "e005", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-15), getTimeInMills(-5)
                        )
                    )
                    add(
                        Evaluation(
                            "e006", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-10), getTimeInMills(0)
                        )
                    )
                }
                "a002" -> {
                    add(
                        Evaluation(
                            "e001", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(0), getTimeInMills(10)
                        )
                    )
                    add(
                        Evaluation(
                            "e002", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-8), getTimeInMills(2)
                        )
                    )
                    add(
                        Evaluation(
                            "e003", "Evaluación de medio termino",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-5), getTimeInMills(5)
                        )
                    )
                    add(
                        Evaluation(
                            "e004", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(0), getTimeInMills(10)
                        )
                    )
                    add(
                        Evaluation(
                            "e005", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-8), getTimeInMills(2)
                        )
                    )
                    add(
                        Evaluation(
                            "e006", "Evaluación de medio termino",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-5), getTimeInMills(5)
                        )
                    )
                }
                "a003" -> {
                    add(
                        Evaluation(
                            "e001", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(-7), getTimeInMills(-2)
                        )
                    )
                    add(
                        Evaluation(
                            "e002", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-15), getTimeInMills(-2)
                        )
                    )
                    add(
                        Evaluation(
                            "e003", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-1), getTimeInMills(0)
                        )
                    )
                    add(
                        Evaluation(
                            "e004", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsBlank(),
                            getTimeInMills(-5), getTimeInMills(-1)
                        )
                    )
                    add(
                        Evaluation(
                            "e005", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsUnfinished(),
                            getTimeInMills(-15), getTimeInMills(-5)
                        )
                    )
                    add(
                        Evaluation(
                            "e006", "Evaluación de fin de curso",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.",
                            getFakeGroupsList()[((0..5).random())], getFakeQuestionsResponded(),
                            getTimeInMills(-10), getTimeInMills(0)
                        )
                    )

                }
                else -> throw Throwable("Alumno no encontrado.")
            }
        }
        Result.Success(fakeEvaluations)
    } catch (e: Throwable) {
        Result.Error(IOException("Error al obtener las evaluaciones del estudiante.", e))
    }

    override suspend fun getProfessorEvaluations(professorId: String): Result<List<Evaluation>> =
        Result.Error(IOException("Operación no soportada para este perfil."))

    private fun getFakeGroupsList() : List<Group> {
        val fakeGroups = arrayListOf<Group>()
        for (i in 1..6) {
            fakeGroups.add(
                Group(
                    "C02${i}-2023",
                    "Dummy Class 10$i",
                    Employee(
                        "p00${i+2}",
                        when (i) {
                            1 -> "Javier Lopez"
                            2 -> "Sheldon Copper"
                            else -> "Jane Doe"
                        }
                    )
                )
            )
        }
        return fakeGroups
    }

    private fun getFakeQuestionsBlank() : List<Question> {
        val fakeQuestions = arrayListOf<Question>()
        fakeQuestions.apply {
            add(
                Question(
                    "q001", "Inicia y termina sus clases puntualmente.",
                    getFakeSkills().filter { it.skillId == "s001" }
                )
            )
            add(
                Question(
                    "q002", "Comentó y discutió con el grupo, el programa de la materia y/o carta descriptiva al inicio del curso (propósito, objetivo, contenido, organización, criterios de evaluación, bibliografía y/o materiales a revisar).",
                    getFakeSkills().filter { it.skillId == "s001" }
                )
            )
            add(
                Question(
                    "q003", "Desarrolla el curso de manera ordenada y cubriendo los objetivos planteados.",
                    getFakeSkills().filter { it.skillId == "s001" }
                )
            )
            add(
                Question(
                    "q004", "Inicia la sesión vinculando la clase con temas anteriores.",
                    getFakeSkills().filter { it.skillId == "s001" }
                )
            )
            add(
                Question(
                    "q005", "Prevé el uso de recursos y materiales necesarios para la clase.",
                    getFakeSkills().filter { it.skillId == "s001" }
                )
            )
            add(
                Question(
                    "q006", "Explica los temas del programa y atiende dudas de manera clara y precisa.",
                    getFakeSkills().filter { it.skillId == "s002" }
                )
            )
            add(
                Question(
                    "q007", "Fomenta el uso correcto de la expresión oral y escrita.",
                    getFakeSkills().filter { it.skillId == "s002" }
                )
            )
            add(
                Question(
                    "q008", "Discute y relaciona ejemplos reales con los temas tratados en clase.",
                    getFakeSkills().filter { it.skillId == "s002" }
                )
            )
            add(
                Question(
                    "q009", "Evalúa de acuerdo con los contenidos y criterios establecidos en el programa y presentados al inicio del curso.",
                    getFakeSkills().filter { it.skillId == "s003" }
                )
            )
            add(
                Question(
                    "q010", "Considera y evalúa las tareas relacionadas con el programa.",
                    getFakeSkills().filter { it.skillId == "s003" }
                )
            )
            add(
                Question(
                    "q011", "Revisa y retroalimenta oportunamente las tareas y trabajos desarrollados en el curso (proyectos, prácticas, exámenes, presentaciones o exposiciones, etc.).",
                    getFakeSkills().filter { it.skillId == "s003" }
                )
            )
            add(
                Question(
                    "q012", "Evalúa el trabajo colaborativo o cooperativo.",
                    getFakeSkills().filter { it.skillId == "s003" }
                )
            )
            add(
                Question(
                    "q013", "Muestra interés por el desempeño de los/as estudiantes.",
                    getFakeSkills().filter { it.skillId == "s004" }
                )
            )
            add(
                Question(
                    "q014", "Muestra disposición para consultarlo/a, dentro y fuera de clase.",
                    getFakeSkills().filter { it.skillId == "s004" }
                )
            )
            add(
                Question(
                    "q015", "Motiva y fomenta la ética, los valores y el respeto de la diversidad (creencias, género, profesión, etnia, libertad de expresión, etc.).",
                    getFakeSkills().filter { it.skillId == "s004" }
                )
            )
            add(
                Question(
                    "q016", "En general, ¿cómo califica usted a su profesor/a?",
                    getFakeSkills().filter { it.skillId == "s005" }
                )
            )
            add(
                Question(
                    "q017", "COMENTARIOS",
                    getFakeSkills().filter { it.skillId == "s005" }, QuestionType.COMMENT
                )
            )
        }
        return fakeQuestions
    }

    private fun getFakeQuestionsResponded() : List<Question> = getFakeQuestionsBlank().map { question ->
        when (question.type) {
            QuestionType.RANGE -> question.answerGrade = (1..5).random()
            QuestionType.YES_NO -> question.answerGrade = (0..1).random()
            QuestionType.OPEN -> question.answerBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque."
                QuestionType.COMMENT -> question.comments = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque."
        }
        question
    }

    private fun getFakeQuestionsUnfinished() : List<Question> =
        getFakeQuestionsBlank().mapIndexed { index, question ->
            when (question.type) {
                QuestionType.RANGE -> question.answerGrade = if (index%2 != 0) (1..5).random() else null
                QuestionType.YES_NO -> question.answerGrade = if (index%2 != 0) (0..1).random() else null
                QuestionType.OPEN -> question.answerBody = if (index%2 != 0) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque." else null
                QuestionType.COMMENT -> question.comments = if (index%2 != 0) "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque." else null
            }
            question
        }

    private fun getFakeSkills() : List<Skill> {
        val fakeSkills = arrayListOf<Skill>()
        fakeSkills.apply {
            add(
                Skill(
                    "s001", "Organización del curso",
                    "", 5,
                    Recommendation(
                        "¡La organización te llevara a tu objetivo!",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque. Integer aliquet, eros tincidunt iaculis ultrices, orci elit rhoncus eros, id efficitur ipsum orci quis sapien. Mauris ornare, dolor sed dictum fringilla, arcu tortor ornare felis, id scelerisque augue felis id lectus.",
                        arrayListOf(Pair("Enseñanza Practica", "https://www.google.com/"))
                    )
                )
            )
            add(
                Skill(
                    "s002", "Dinámica pedagógica",
                    "", 1,
                    Recommendation(
                        "Fomenta el aprendizaje activo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque. Integer aliquet, eros tincidunt iaculis ultrices, orci elit rhoncus eros, id efficitur ipsum orci quis sapien. Mauris ornare, dolor sed dictum fringilla, arcu tortor ornare felis, id scelerisque augue felis id lectus.",
                        arrayListOf(Pair("Enseñanza Normal", "https://www.google.com/"))
                    )
                )
            )
            add(
                Skill(
                    "s003", "Evaluación y criterios de calificación",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.", 6,
                    Recommendation(
                        "La retroalimentación es la enseñanza mas grande",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque. Integer aliquet, eros tincidunt iaculis ultrices, orci elit rhoncus eros, id efficitur ipsum orci quis sapien. Mauris ornare, dolor sed dictum fringilla, arcu tortor ornare felis, id scelerisque augue felis id lectus.",
                        arrayListOf(Pair("Evaluacion 360", "https://www.google.com/"))
                    )
                )
            )
            add(
                Skill(
                    "s004", "Rasgos académicos y personales",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.", 2,
                    Recommendation(
                        "Aprendizaje con calidad humana",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque. Integer aliquet, eros tincidunt iaculis ultrices, orci elit rhoncus eros, id efficitur ipsum orci quis sapien. Mauris ornare, dolor sed dictum fringilla, arcu tortor ornare felis, id scelerisque augue felis id lectus.",
                        arrayListOf(Pair("Empatia y Aprendizaje", "https://www.google.com/"))
                    )
                )
            )
            add(
                Skill(
                    "s005", "General",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vel nunc neque.", 10
                )
            )
        }
        return fakeSkills
    }

    private fun getTimeInMills(days: Int) = System.currentTimeMillis() + (1000 * 60 * 60 * 24 * days)

}