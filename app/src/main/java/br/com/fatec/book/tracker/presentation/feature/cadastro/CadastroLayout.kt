package br.com.fatec.book.tracker.presentation.feature.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.R
import br.com.fatec.book.tracker.ui.components.button.BookTrackerButton
import br.com.fatec.book.tracker.ui.components.divider.TextDivider
import br.com.fatec.book.tracker.ui.components.textfield.BookTrackerTextField
import br.com.fatec.book.tracker.ui.images.BookTrackerDrawableResources

@Composable
fun CadastroLayout(
    modifier: Modifier = Modifier,
    onRegister: (email: String, password: String) -> Unit = { _, _ -> },
    onLogin: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 34.dp, vertical = 52.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Cadastre-se",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Text(
            modifier = Modifier.padding(bottom = 60.dp),
            text = "Insira as informações abaixo",
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(50.dp),
        ) {
            BookTrackerTextField(
                text = email,
                onValueChange = {
                    email = it
                },
                title = "E-mail",
                label = "Informe o E-mail",
            )

            BookTrackerTextField(
                text = password,
                onValueChange = {
                    password = it
                },
                title = "Senha",
                label = "Informe a Senha",
            )

            BookTrackerTextField(
                text = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                title = "Confirme a Senha",
                label = "Confirme a Senha",
            )
        }

        Spacer(modifier = modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BookTrackerButton(
                text = "Cadastrar",
                onClick = {
                    onRegister(email, password)
                },
                enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
                        && password == confirmPassword
            )

            BookTrackerButton(
                text = "Entrar",
                onClick = onLogin,
            )
        }

        TextDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.clickable(
                    onClick = {},
                ),
                painter = BookTrackerDrawableResources.Images.imagemGoogle,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun CadastroLayoutPreview() {
    MaterialTheme {
        CadastroLayout()
    }
}
