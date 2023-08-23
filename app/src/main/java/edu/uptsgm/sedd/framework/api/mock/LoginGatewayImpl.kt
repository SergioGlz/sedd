package edu.uptsgm.sedd.framework.api.mock

import edu.uptsgm.sedd.application.model.UserSesion
import edu.uptsgm.sedd.application.model.UserType
import edu.uptsgm.sedd.interfaceAdapter.operators.Result
import edu.uptsgm.sedd.interfaceAdapter.gateway.LoginGateway
import java.io.IOException
import java.util.UUID

class LoginGatewayImpl: LoginGateway {

    override suspend fun login(username: String, password: String): Result<UserSesion> = try {
        when (username) {
            "a001@uptsgm.edu.mx" -> Result.Success(
                UserSesion("a001", "Adrian González", UserType.STUDENT)
            )
            "a002@uptsgm.edu.mx" -> Result.Success(
                UserSesion("a002", "Karen Perez", UserType.STUDENT)
            )
            "a003@uptsgm.edu.mx" -> Result.Success(
                UserSesion("a003", "Silvino Hernandez", UserType.STUDENT)
            )
            "p001@uptsgm.edu.mx" -> Result.Success(
                UserSesion("p001", "Javier Lopez", UserType.PROFESSOR)
            )
            "p002@uptsgm.edu.mx" -> Result.Success(
                UserSesion("p002", "Sheldon Copper", UserType.PROFESSOR)
            )
            "d001@uptsgm.edu.mx" -> Result.Success(
                UserSesion("d001", "Manuel Camacho", UserType.DIRECTOR)
            )
            "d002@uptsgm.edu.mx" -> Result.Success(
                UserSesion("d002", "Fernando Suarez", UserType.DIRECTOR)
            )
            "calidad.institucional@uptsgm.edu.mx" -> Result.Success(
                UserSesion("admin", "Calidad Institucional", UserType.ADMIN)
            )
            else -> Result.Error(IOException("Usuario no válido"))
        }
    } catch (e: Throwable) {
        Result.Error(IOException("Error al iniciar sesión", e))
    }

}