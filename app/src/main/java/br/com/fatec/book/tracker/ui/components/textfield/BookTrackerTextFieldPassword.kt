package br.com.fatec.book.tracker.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources

@Composable
fun BookTrackerTextFieldPassword(
    text: String,
    title: String?,
    label: String? = null,
    customStyle: TextStyle? = null,
    onValueChange: (String) -> Unit = {},
    keyboardActions: KeyboardActions? = null,
) {
    val focusManager = LocalFocusManager.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

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
                singleLine = true,
                onValueChange = onValueChange,
                textStyle = customStyle ?: MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = text },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = keyboardActions ?: KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                    onNext = { focusManager.moveFocus(FocusDirection.Next) },
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (text.isEmpty()) {
                                Text(
                                    text = label ?: "",
                                    color = Color.Gray,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            innerTextField()
                        }

                        val image =
                            if (passwordVisible) BookTrackerDrawableResources.Vectors.ocultarSenha
                            else BookTrackerDrawableResources.Vectors.mostrarSenha

                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(14.dp),
                                imageVector = image,
                                contentDescription = null,
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun BookTrackerTextFieldPasswordPreview() {
    MaterialTheme {
        BookTrackerTextFieldPassword(
            text = "",
            title = "Senha",
            label = "Informe a Senha",
        )
    }
}
