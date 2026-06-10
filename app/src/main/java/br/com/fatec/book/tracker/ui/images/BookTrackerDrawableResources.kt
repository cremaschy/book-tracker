package br.com.fatec.book.tracker.ui.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.com.fatec.book.tracker.R

object BookTrackerDrawableResources {
    object Images {
        val imagemLogo
            @Composable
            get() = painterResource(id = R.drawable.imagem_logo_booktracker)

        val bookTracker
            @Composable
            get() = painterResource(id = R.drawable.imagem_booktracker)

        val livro
            @Composable
            get() = painterResource(id = R.drawable.imagem_livro)
    }

    object Vectors {
        val mostrarSenha
            @Composable
            get() = vectorResource(id = R.drawable.ic_mostrar_senha)

        val ocultarSenha
            @Composable
            get() = vectorResource(id = R.drawable.ic_ocultar_senha)

        val voltar
            @Composable
            get() = vectorResource(id = R.drawable.ic_arrow_back)

        val buscar
            @Composable
            get() = vectorResource(id = R.drawable.ic_search)
    }
}
