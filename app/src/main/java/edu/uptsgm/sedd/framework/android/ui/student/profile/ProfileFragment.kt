package edu.uptsgm.sedd.framework.android.ui.student.profile

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.Constants
import edu.uptsgm.sedd.application.Constants.EXTRA_STUDENT_KEY
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Student

class ProfileFragment : Fragment() {

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            student = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(EXTRA_STUDENT_KEY, Student::class.java)
            } else {
                bundle.getSerializable(EXTRA_STUDENT_KEY) as Student?
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        student?.let {student ->
            view.findViewById<TextView>(R.id.profile_name_lbl).text = student.displayName
            view.findViewById<TextView>(R.id.profile_mayor_lbl).text = student.mayor
            student.groups?.let { groups ->
                view.findViewById<RecyclerView>(R.id.groups_recycler_view)?.apply {
                    adapter = GroupsAdapter(groups)
                    layoutManager = LinearLayoutManager(this@ProfileFragment.context, LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(student: Student) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_STUDENT_KEY, student)
                }
            }
    }
}