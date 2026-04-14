package br.com.fatec.book.tracker.ui.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.com.fatec.book.tracker.R

object BookTrackerDrawableResources {
    object Images {
        val imagemGoogle
            @Composable
            get() = painterResource(id = R.drawable.imagem_google)
    }

    object Vectors {
        val mostrarSenha
            @Composable
            get() = vectorResource(id = R.drawable.ic_mostrar_senha)

        val ocultarSenha
            @Composable
            get() = vectorResource(id = R.drawable.ic_ocultar_senha)
    }
}
