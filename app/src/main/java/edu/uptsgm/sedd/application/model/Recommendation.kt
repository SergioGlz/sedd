package edu.uptsgm.sedd.application.model

import java.io.Serializable

data class Recommendation(
    val title: String,
    val description: String,
    val activities: List<Pair<String,String>>
) : Serializable
