package com.quickbirdstudios.surveykit.result

import android.os.Parcelable
import com.quickbirdstudios.surveykit.Identifier
import java.util.*

interface Result : Parcelable {
    val id: Identifier
    val startDate: Date
    var endDate: Date
}
