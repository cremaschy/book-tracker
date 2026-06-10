package br.com.fatec.book.tracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus
import br.com.fatec.book.tracker.ui.theme.BookTrackerTheme

@Composable
fun BookTrackerDropdownFiltro(
    selected: ReadingStatus?,
    options: List<ReadingStatus>,
    onSelected: (ReadingStatus) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .border(2.dp, Color.Black)
                .background(Color.White)
                .clickable {
                    expanded = true
                }
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selected?.status ?: "Selecione",
                style = MaterialTheme.typography.bodyMedium,
                color = if (selected == null) Color.Gray else Color.Black,
                fontStyle = if (selected == null) FontStyle.Italic else FontStyle.Normal,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(option.status)
                    },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
@Preview
@Composable
fun BookTrackerDropdownFiltroPreview() {
    BookTrackerTheme {
        BookTrackerDropdownFiltro(
            selected = ReadingStatus.WANT_TO_READ,
            options = ReadingStatus.entries,
            onSelected = {}
        )
    }
}
