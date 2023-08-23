package edu.uptsgm.sedd.framework.android.ui.login

import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.uptsgm.sedd.R
import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.port.LoginInputPort
import edu.uptsgm.sedd.interfaceAdapter.operators.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: UserSesion? = null,
    val error: Int? = null
)

class LoginViewModel(private val loginInputPort: LoginInputPort) : ViewModel() {

    class Factory(private val loginInputPort: LoginInputPort) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(loginInputPort) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _loginForm = MutableStateFlow(LoginFormState())
    val loginFormState: StateFlow<LoginFormState> = _loginForm.asStateFlow()

    private val _loginResult = MutableStateFlow(LoginResult())
    val loginResult: StateFlow<LoginResult> = _loginResult.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginInputPort.login(username, password)

            if (result is Result.Success) {
                _loginResult.update { LoginResult(success = result.data) }
            } else {
                _loginResult.update { LoginResult(error = R.string.login_failed) }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 6
    }
}