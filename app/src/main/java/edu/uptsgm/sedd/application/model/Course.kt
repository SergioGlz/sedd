package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Course(
    val title: String,
    val description: String,
    val link: String,
    val skillsAssociated: List<Skill>
) : Serializable

