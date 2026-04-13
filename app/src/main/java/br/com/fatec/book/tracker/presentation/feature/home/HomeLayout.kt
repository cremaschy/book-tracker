package br.com.fatec.book.tracker.presentation.feature.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.R
import br.com.fatec.book.tracker.domain.model.Home
import br.com.fatec.book.tracker.ui.components.card.CardAdicionarLivro
import br.com.fatec.book.tracker.ui.components.card.CardLivroAtual
import br.com.fatec.book.tracker.ui.components.card.CardProximasLeituras

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeLayout(
    home: Home,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .padding(horizontal = 34.dp, vertical = 52.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Bem vindo, ${home.nome}",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge,
            )

            Image(
                contentDescription = null,
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.logo_booktracker),
            )
        }

        // CARD FIXO


        // 🔥 PAGER ENTRE OS DOIS COMPONENTES
        Column {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                when (page) {
                    0 -> CardAdicionarLivro(
                        onClick = {},
                    )

                    1 -> CardLivroAtual()
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🔵 INDICADORES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(2) { index ->
                    val isSelected = pagerState.currentPage == index

                    val size by animateDpAsState(
                        targetValue = if (isSelected) 10.dp else 8.dp,
                        label = ""
                    )

                    val color by animateColorAsState(
                        targetValue = if (isSelected) Color.Black else Color.LightGray,
                        label = ""
                    )

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(size)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }

        CardProximasLeituras()
    }
}

@Preview
@Composable
fun HomeLayoutPreview() {
    MaterialTheme {
        HomeLayout(
            home = Home(
                nome = "Gustavo",
                ofensiva = 3,
            )
        )
    }
}
