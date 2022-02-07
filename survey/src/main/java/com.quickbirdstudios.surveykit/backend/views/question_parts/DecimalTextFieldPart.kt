package com.quickbirdstudios.surveykit.backend.views.question_parts

import android.content.Context
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.View
import androidx.annotation.StringRes
import com.quickbirdstudios.surveykit.R

internal class DecimalTextFieldPart(context: Context) : TextFieldPart(context) {

    init {
        id = R.id.decimalFieldPartField
        this.field.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        this.field.keyListener = (DigitsKeyListener.getInstance("1234567890."))
        this.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    companion object {
        fun withHint(context: Context, @StringRes hint: Int) = DecimalTextFieldPart(
            context
        ).apply {
            field.hint = context.getString(hint)
        }
    }
}
