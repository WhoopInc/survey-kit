package com.quickbirdstudios.surveykit.backend.views.questions

import android.content.Context
import android.view.Gravity
import androidx.annotation.StringRes
import com.quickbirdstudios.surveykit.AnswerFormat
import com.quickbirdstudios.surveykit.R
import com.quickbirdstudios.surveykit.StepIdentifier
import com.quickbirdstudios.surveykit.backend.helpers.extensions.afterTextChanged
import com.quickbirdstudios.surveykit.backend.views.question_parts.DecimalTextFieldPart
import com.quickbirdstudios.surveykit.backend.views.step.QuestionView
import com.quickbirdstudios.surveykit.result.QuestionResult
import com.quickbirdstudios.surveykit.result.question_results.FloatQuestionResult

internal class DecimalQuestionView(
    context: Context,
    id: StepIdentifier,
    isOptional: Boolean,
    title: String?,
    text: String?,
    nextButtonText: String,
    skipButtonText: String,
    @StringRes private val hintText: Int? = null,
    private val answerFormat: AnswerFormat.DecimalAnswerFormat,
    private val preselected: Float? = null
) : QuestionView(context, id, isOptional, title, text, nextButtonText, skipButtonText) {

    //region Members

    private lateinit var questionAnswerView: DecimalTextFieldPart

    //endregion

    //region Overrides

    override fun createResults(): QuestionResult =
        FloatQuestionResult(
            id = id,
            startDate = startDate,
            answer = questionAnswerView.field.text.toString().parseToFloatOrNull(),
            stringIdentifier = questionAnswerView.field.text.toString()
        )

    override fun isValidInput(): Boolean = isOptional || questionAnswerView.field.text.isNotBlank()

    override fun setupViews() {
        super.setupViews()

        questionAnswerView =
            content.add(DecimalTextFieldPart.withHint(context, hintText ?: R.string.empty))
        questionAnswerView.field.gravity = Gravity.CENTER
        questionAnswerView.field.hint = answerFormat.hint
        questionAnswerView.field.afterTextChanged { footer.canContinue = isValidInput() }
        val alreadyEntered = preselected?.toString() ?: answerFormat.defaultValue?.toString()
        questionAnswerView.field.setText(alreadyEntered ?: "")
    }

    //endregion

    //region Private Helpers

    private fun String.parseToFloatOrNull(): Float? {
        return try {
            this.toFloat()
        } catch (e: NumberFormatException) {
            null
        }
    }

    //endregion
}
