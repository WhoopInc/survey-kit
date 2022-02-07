package com.quickbirdstudios.surveykit.result.question_results

import android.os.Parcelable
import com.quickbirdstudios.surveykit.Identifier
import com.quickbirdstudios.surveykit.result.QuestionResult
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class IntroQuestionResult(
    override val id: Identifier,
    override val startDate: Date,
    override var endDate: Date = Date(),
    override val stringIdentifier: String = "",
    override val answer: String? = null
) : QuestionResult, Parcelable
