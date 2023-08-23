package edu.uptsgm.sedd.framework.android.ui.student.home

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationBarView
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.Constants
import edu.uptsgm.sedd.application.model.Evaluation
import edu.uptsgm.sedd.application.model.Student
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.startActivityAndClearStack
import edu.uptsgm.sedd.databinding.ActivityStudentHomeBinding
import edu.uptsgm.sedd.framework.android.ui.login.LoginActivity
import edu.uptsgm.sedd.framework.android.ui.student.evaluations.EvaluationsFragment
import edu.uptsgm.sedd.framework.android.ui.student.profile.ProfileFragment
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class StudentHomeActivity : AppCompatActivity(), AndroidScopeComponent {

    private lateinit var binding: ActivityStudentHomeBinding

    private val studentHomeViewModel: StudentHomeViewModel by viewModel()

    private val onItemSelectedListener = NavigationBarView.OnItemSelectedListener { menuItem ->
        binding.studentHomePager.setCurrentItem(menuItem.order, false)
        true
    }

    override val scope: Scope by activityScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.mainToolbar.apply {
            elevation = 8.0f
            this@StudentHomeActivity.setSupportActionBar(this)
        }


        collectFlows()

        intent.extras?.let { bundle ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(Constants.EXTRA_USER_SESSION_KEY, UserSesion::class.java)
            } else {
                bundle.getSerializable(Constants.EXTRA_USER_SESSION_KEY) as UserSesion
            }
        }?.let { userSession ->
            studentHomeViewModel.getStudentData(userSession)
        } ?: also {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_logout -> {
            startActivityAndClearStack(LoginActivity::class.java)
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun collectFlows() {
        var student: Student? = null
        lifecycleScope.launch {
            studentHomeViewModel.studentInfo.collect { getStudentResult ->
                if (getStudentResult.error == null && getStudentResult.success == null) return@collect

                if (getStudentResult.error != null) {
                    binding.studentHomeProgress.visibility = View.GONE
                    showServiceError(getStudentResult.error)
                    super.onBackPressed()
                }
                if (getStudentResult.success != null) {
                    student = getStudentResult.success
                    studentHomeViewModel.getEvaluations(getStudentResult.success)
                }
            }
        }

        lifecycleScope.launch {
            studentHomeViewModel.studentEvaluations.collect { evaluationsResult ->
                if (evaluationsResult.error == null && evaluationsResult.success == null) return@collect

                binding.studentHomeProgress.visibility = View.GONE
                if (evaluationsResult.error != null) {
                    showServiceError(evaluationsResult.error)
                    startActivityAndClearStack(LoginActivity::class.java)
                }
                if (evaluationsResult.success != null) {
                    initBottomNavigation(student!!, evaluationsResult.success as ArrayList<Evaluation>)
                }
            }
        }
    }

    private fun initBottomNavigation(student: Student, evaluations: ArrayList<Evaluation>) {
        binding.studentHomeNavigation.isEnabled = false
        val fragments = arrayListOf<Fragment>().apply {
            add(
                EvaluationsFragment.newInstance(
                evaluations.filter { it.endTime > System.currentTimeMillis() } as ArrayList<Evaluation>
            ))
            add(
                EvaluationsFragment.newInstance(
                evaluations.filter { it.endTime < System.currentTimeMillis() } as ArrayList<Evaluation>
            ))
            add(
                ProfileFragment.newInstance(student)
            )
        }

        binding.studentHomePager.apply {
            isUserInputEnabled = false
            offscreenPageLimit = fragments.size
            adapter = StudentViewPagerAdapter(fragments, this@StudentHomeActivity)
        }

        binding.studentHomeNavigation.setOnItemSelectedListener(onItemSelectedListener)
    }

    private fun showServiceError(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}