package edu.uptsgm.sedd.framework.android.ui.student.evaluations

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.Constants.EXTRA_EVALUATIONS_LIST_KEY
import edu.uptsgm.sedd.application.model.Evaluation

class EvaluationsFragment : Fragment() {

    private var evaluations: List<Evaluation>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            evaluations = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList(EXTRA_EVALUATIONS_LIST_KEY, Evaluation::class.java)
            } else {
                bundle.getParcelableArrayList(EXTRA_EVALUATIONS_LIST_KEY)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_evaluations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        evaluations?.let {
            view.findViewById<RecyclerView>(R.id.evaluations_recycler_view)?.apply {
                adapter = EvaluationsAdapter(it)
                layoutManager = LinearLayoutManager(this@EvaluationsFragment.context, LinearLayoutManager.VERTICAL, false)
                if (it.isEmpty()) visibility = View.GONE
            }
            if (it.isEmpty()) {
                view.findViewById<TextView>(R.id.evaluations_selector_msg)?.visibility = View.GONE
                view.findViewById<ConstraintLayout>(R.id.pending_evaluations_empty_state)?.visibility = View.VISIBLE
            }
        } ?: also {
            view.findViewById<TextView>(R.id.evaluations_selector_msg)?.visibility = View.GONE
            view.findViewById<RecyclerView>(R.id.evaluations_recycler_view)?.visibility = View.GONE
            view.findViewById<ConstraintLayout>(R.id.pending_evaluations_empty_state)?.visibility = View.VISIBLE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(evaluationsList: ArrayList<Evaluation>) =
            EvaluationsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_EVALUATIONS_LIST_KEY, evaluationsList)
                }
            }
    }
}