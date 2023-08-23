package edu.uptsgm.sedd.application.port

interface LogoutInputPort {
    suspend fun logout()
}

interface LogoutOutputPort {
    suspend fun logout()
}