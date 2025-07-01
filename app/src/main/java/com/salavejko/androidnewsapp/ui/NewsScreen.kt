package com.salavejko.androidnewsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.salavejko.androidnewsapp.domain.model.RatingArticleFilter
import com.salavejko.androidnewsapp.domain.model.TitleArticleFilter

@Composable
fun NewsScreen(modifier: Modifier = Modifier, newsViewModel: NewsViewModel = hiltViewModel()) {
    val news by newsViewModel.articles.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        var titleFilter by remember { mutableStateOf("") }
        var ratingFilter by remember { mutableStateOf("") }

        OutlinedTextField(
            value = titleFilter,
            onValueChange = { titleFilter = it },
            label = { Text("Title filter") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ratingFilter,
            onValueChange = { ratingFilter = it },
            label = { Text("Rating filter") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val filters = listOfNotNull(
                titleFilter.takeIf { it.isNotEmpty() }?.let { TitleArticleFilter(it) },
                ratingFilter.toIntOrNull()?.let { RatingArticleFilter(it) },
            )

            newsViewModel.onButtonClicked(filters)
        }) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(news) { article ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(article.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(article.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = article.imageUrl,
                        contentDescription = null,
                        placeholder = ColorPainter(
                            Color(article.placeholderColor)
                        ),
                        error = ColorPainter(
                            Color(article.placeholderColor)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}