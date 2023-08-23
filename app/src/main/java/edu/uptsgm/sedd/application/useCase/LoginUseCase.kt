package edu.uptsgm.sedd.application.useCase

import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.port.LoginInputPort
import edu.uptsgm.sedd.application.port.LoginOutputPort
import edu.uptsgm.sedd.interfaceAdapter.operators.Result

class LoginUseCase(private val loginOutputPort: LoginOutputPort) : LoginInputPort {

    override suspend fun login(username: String, password: String): Result<UserSesion> =
        loginOutputPort.login(username, password)

}