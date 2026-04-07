package br.com.fatec.book.tracker.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.ui.transformations.InputTextMask
import br.com.fatec.book.tracker.ui.transformations.MaskVisualTransformation

@Composable
fun BookTrackerTextField(
    text: String,
    title: String?,
    label: String? = null,
    maxLength: Int? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean? = false,
    customStyle: TextStyle? = null,
    inputTextMask: InputTextMask? = null,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = Done),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = title ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black)
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            BasicTextField(
                value = text,
                enabled = enabled,
                readOnly = readOnly == true,
                onValueChange = {
                    if (inputTextMask != null) {
                        if (it.length <= inputTextMask.length) onValueChange(it)
                    } else {
                        maxLength?.let { max ->
                            if (it.length <= max) onValueChange(it)
                        } ?: onValueChange(it)
                    }
                },
                visualTransformation = if (inputTextMask != null) {
                    MaskVisualTransformation(inputTextMask)
                } else {
                    visualTransformation
                },
                keyboardActions = keyboardActions ?: KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                ),
                interactionSource = interactionSource,
                keyboardOptions = inputTextMask?.keyboardOptions ?: keyboardOptions,
                singleLine = singleLine,
                textStyle = customStyle ?: MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = text },
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = label ?: "",
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun BookTrackerTextFieldPreview() {
    MaterialTheme {
        BookTrackerTextField(
            text = "",
            title = "Email",
            label = "Informe o E-mail",
        )
    }
}
