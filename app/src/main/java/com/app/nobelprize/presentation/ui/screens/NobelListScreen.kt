package com.app.nobelprize.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.nobelprize.data.model.NobelPrize
import com.app.nobelprize.presentation.viewModels.NobelViewModel

@Composable
fun NobelListScreen(
    viewModel: NobelViewModel = hiltViewModel(),
    onItemClick: (NobelPrize) -> Unit
) {
    val prizes by viewModel.prizes.collectAsState()
    LazyColumn {
        itemsIndexed(prizes) { index, prize ->
            PrizeItem(prize) { onItemClick(prize) }
            if (index == prizes.lastIndex) {
                LaunchedEffect(Unit) {
                    viewModel.loadMorePrizes()
                }
            }
        }
        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
    }


}

@Composable
fun PrizeItem(prize: NobelPrize, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp), colors = CardDefaults.cardColors(
            contentColor = Color.Black, containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Year: ${prize.awardYear}")
            Text(text = "Category: ${prize.category.en}")
            prize.laureates?.firstOrNull()?.let {
                Text(text = "Winner: ${it.knownName?.en ?: it.fullName?.en ?: "Unknown"}")
            }
        }
    }
}
