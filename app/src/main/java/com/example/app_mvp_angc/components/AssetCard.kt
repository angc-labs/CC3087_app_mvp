package com.example.app_mvp_angc.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AssetCard (
    modifier: Modifier,
    assetName: String,
    symbol: String,
    price: Double?,
    percentageChange: Double?,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = assetName,
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = symbol,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$${price}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${String.format("%.2f", percentageChange)}%",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier
                ) {
                    Text(text = "Ver")
                }
            }
        }
    }
}

@Composable
@Preview
fun AssetCardPreview() {
    AssetCard(
        modifier = Modifier,
        assetName = "Bitcoin",
        symbol = "BTC",
        price = 25000.0,
        percentageChange = 1.5
    )
}