package br.com.fatec.book.tracker.presentation.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginIntent
import br.com.fatec.book.tracker.presentation.feature.login.state.LoginViewState
import br.com.fatec.book.tracker.ui.components.button.BookTrackerButton
import br.com.fatec.book.tracker.ui.components.textfield.BookTrackerTextField
import br.com.fatec.book.tracker.ui.components.textfield.BookTrackerTextFieldPassword

@Composable
fun LoginLayout(
    state: LoginViewState,
    modifier: Modifier = Modifier,
    onIntent: (LoginIntent) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Acesse",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 60.dp),
            text = "Com e-mail e senha para entrar",
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp),
        ) {
            BookTrackerTextField(
                text = state.email,
                onValueChange = {
                    onIntent(LoginIntent.OnEmailChanged(it))
                },
                title = "E-mail",
                label = "Informe o E-mail",
            )

            BookTrackerTextFieldPassword(
                text = state.senha,
                onValueChange = {
                    onIntent(LoginIntent.OnPasswordChanged(it))
                },
                title = "Senha",
                label = "Informe a Senha",
            )
        }

        Text(
            modifier = Modifier
                .clickable(
                    onClick = {},
                )
                .padding(top = 12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            text = "Esqueci minha senha",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = modifier.weight(1f))

        BookTrackerButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Acessar",
            onClick = {
                onIntent(LoginIntent.OnLoginClicked)
            },
            enabled = state.isLoginEnable
        )
    }
}

@Preview
@Composable
fun LoginLayoutPreview() {
    MaterialTheme {
        LoginLayout(
            state = LoginViewState(),
            onIntent = {},
        )
    }
}
