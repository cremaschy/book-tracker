package br.com.fatec.book.tracker.ui.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource

@Composable
fun ImageVector.asPainter(): Painter {
    return rememberVectorPainter(this)
}

@Composable
fun Painter.tint(color: Color) = apply {
    ColorFilter.tint(color)
}

@Composable
fun vectorResource(id: Int): ImageVector {
    return ImageVector.vectorResource(id = id)
}
