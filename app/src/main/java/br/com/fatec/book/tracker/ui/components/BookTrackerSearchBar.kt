package br.com.fatec.book.tracker.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fatec.book.tracker.domain.model.livro.Livro

@Composable
fun BookTrackerSearchBar(
    items: List<Livro> = emptyList()
) {
    var search by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val filteredItems = remember(search) {

        if (search.isBlank()) {
            items
        } else {
            items.filter {
                it.titulo.contains(search, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier.weight(1f),
                enter = expandHorizontally(
                    expandFrom = Alignment.End
                ) + fadeIn(),
                exit = shrinkHorizontally(
                    shrinkTowards = Alignment.End
                ) + fadeOut()
            ) {

                OutlinedTextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Black,
                        unfocusedBorderColor = Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    placeholder = {
                        Text("Pesquisar usuário")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    trailingIcon = {

                        IconButton(
                            onClick = {

                                if (search.isEmpty()) {
                                    expanded = false
                                } else {
                                    search = ""
                                }
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            IconButton(
                onClick = {
                    expanded = true
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Pesquisar"
                )
            }
        }
    }
}

@Preview
@Composable
fun BookTrackerSearchBarPreview() {
    MaterialTheme {
        BookTrackerSearchBar()
    }
}
