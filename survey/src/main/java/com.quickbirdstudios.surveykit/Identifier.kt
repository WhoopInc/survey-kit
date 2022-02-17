package com.quickbirdstudios.surveykit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
open class Identifier : Parcelable

@Parcelize
data class TaskIdentifier(val id: String = UUID.randomUUID().toString()) :
    Identifier(),
    Parcelable

@Parcelize
data class StepIdentifier(
    val id: String = UUID.randomUUID().toString()
) : Identifier(), Parcelable
