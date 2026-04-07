package br.com.fatec.book.tracker.ui.transformations

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

sealed class InputTextMask {
    abstract val length: Int
    abstract val mask: String
    abstract val keyboardOptions: KeyboardOptions

    data class Custom(
        override val length: Int,
        override val mask: String,
        override val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    ) : InputTextMask()

    data class Date(
        override val keyboardOptions: KeyboardOptions =
            KeyboardOptions(keyboardType = KeyboardType.Number),
    ) : InputTextMask() {
        override val length: Int = 8
        override val mask: String = "##/##/####"
    }

    data class MonthYear(
        override val keyboardOptions: KeyboardOptions =
            KeyboardOptions(keyboardType = KeyboardType.Number),
    ) : InputTextMask() {
        override val length: Int = 7
        override val mask: String = "##/####"
    }
}
