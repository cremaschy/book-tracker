package br.com.fatec.book.tracker.ui.components.bottom.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.livro.ReadingStatus

@Composable
fun StatusBottomSheet(
    selected: ReadingStatus,
    onSelected: (ReadingStatus) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = "Situação da Leitura",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        ReadingStatus.entries.forEach { status ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelected(status)
                    }
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == status,
                    onClick = {
                        onSelected(status)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = status.status
                )
            }
        }
    }
}
