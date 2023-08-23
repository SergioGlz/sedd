package edu.uptsgm.sedd.application.model

import java.io.Serializable

/**
 * Data class that captures user information for logged in users retrieved from LoginGateway
 */
data class UserSesion(
    val userId: String,
    val displayName: String,
    val type: UserType
) : Serializable

enum class UserType(typeId: Int) {
    STUDENT(0),
    PROFESSOR(1),
    DIRECTOR(2),
    ADMIN(3)
}