package edu.uptsgm.sedd.framework.android.ui.student.evaluations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.dateFormat
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Question
import edu.uptsgm.sedd.databinding.ItemEvaluationBinding

class EvaluationsAdapter(private val evaluations: List<Evaluation>) : RecyclerView.Adapter<EvaluationsAdapter.EvaluationsViewHolder>() {

    inner class EvaluationsViewHolder(val dataBinding: ItemEvaluationBinding) : RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluationsViewHolder = EvaluationsViewHolder(
        ItemEvaluationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: EvaluationsViewHolder, position: Int) {
        holder.dataBinding.iconSrc = getIconSrc(evaluations[position])
        holder.dataBinding.evaluationName = evaluations[position].evaluationName
        holder.dataBinding.evaluationDescription = evaluations[position].evaluationDescription
        holder.dataBinding.group = evaluations[position].group.groupId
        holder.dataBinding.startTime = evaluations[position].startTime.dateFormat()
        holder.dataBinding.endTime = evaluations[position].endTime.dateFormat()
    }

    override fun getItemCount(): Int = evaluations.size

    @IdRes
    private fun getIconSrc(evaluation: Evaluation) : Int {
        val questionsStatus = evaluation.questions.filter { it.answerBody != null || it.answerGrade != null || it.comments != null }
        return when {
            questionsStatus.size == evaluation.questions.size -> R.drawable.ic_completed
            System.currentTimeMillis() >= evaluation.endTime -> R.drawable.ic_off_time
            questionsStatus.isEmpty() -> R.drawable.ic_new
            questionsStatus.size < evaluation.questions.size -> R.drawable.ic_in_progress
            else -> R.drawable.ic_not_found
        }
    }

}