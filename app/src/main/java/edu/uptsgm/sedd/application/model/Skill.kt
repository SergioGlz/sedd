package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Skill(
    val skillId: String?,
    val skillName: String,
    val skillDescription: String,
    val skillValue: Int,
    val recommendation: Recommendation? = null
) : Serializable
