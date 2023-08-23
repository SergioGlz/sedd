package edu.uptsgm.sedd.application.port

import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.interfaceAdapter.operators.Result

interface LoginInputPort {
    suspend fun login(username: String, password: String): Result<UserSesion>
}

interface LoginOutputPort {
    suspend fun login(username: String, password: String): Result<UserSesion>
}