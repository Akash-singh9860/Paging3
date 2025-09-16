package com.app.nobelprize.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.nobelprize.data.model.NobelPrize

@Composable
fun NobelDetailScreen(prize: NobelPrize) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Year: ${prize.awardYear}", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Category: ${prize.category.en}", style = MaterialTheme.typography.bodyLarge)
        prize.laureates?.forEach {
            Text(text = "Winner: ${it.knownName?.en ?: it.fullName?.en ?: "Unknown"}")
            it.motivation?.let { m -> Text(text = "Motivation: ${m.en}") }
        }
    }
}
