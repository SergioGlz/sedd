package edu.uptsgm.sedd.application.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Evaluation(
    val evaluationId: String,
    val evaluationName: String,
    val evaluationDescription: String,
    var group: @RawValue Group,
    val questions: @RawValue List<Question>,
    val startTime: Long,
    val endTime: Long
) : Parcelable