package edu.uptsgm.sedd.framework.android.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.Constants
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.startActivityAndClearStack
import edu.uptsgm.sedd.databinding.ActivityLoginBinding
import edu.uptsgm.sedd.framework.android.ui.student.home.StudentHomeActivity
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModel.Factory(get()))[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.loginUsernameInput
        val password = binding.loginPasswordInput
        val login = binding.loginButton
        val loading = binding.loadingProgress

        lifecycleScope.launch {
            loginViewModel.loginFormState.collect { loginState ->
                // disable login button unless both username / password is valid
                login.isEnabled = loginState.isDataValid

                if (loginState.usernameError != null) {
                    username.error = getString(loginState.usernameError)
                }
                if (loginState.passwordError != null) {
                    password.error = getString(loginState.passwordError)
                }
            }
        }

        lifecycleScope.launch {
            loginViewModel.loginResult.collect { loginResult ->
                if (loginResult.error == null && loginResult.success == null) return@collect

                loading.visibility = View.GONE
                if (loginResult.error != null) {
                    showLoginFailed(loginResult.error)
                }
                if (loginResult.success != null) {
                    loginSuccessful(loginResult.success)
                }
            }
        }

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun loginSuccessful(model: UserSesion) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName

        Intent(this, StudentHomeActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_SESSION_KEY, model)
        }.also { startActivityAndClearStack(it) }

        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()

        setResult(Activity.RESULT_OK)

        //Complete and destroy login activity once successful
        finish()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}