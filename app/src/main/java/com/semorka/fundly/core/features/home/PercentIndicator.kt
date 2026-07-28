package com.semorka.fundly.core.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.core.ui.theme.MadimiFontFamily
import com.semorka.fundly.core.utils.trimZeroDecimal

@Composable
fun PercentIndicator(
    percent: Double,
    modifier: Modifier = Modifier,
    fontSize: Int = 40
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(10.dp)
    ){
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                "${percent.trimZeroDecimal()}%",
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = MadimiFontFamily,
                fontSize = fontSize.sp
            )
        }
    }
}

@Preview
@Composable private fun PercentPreview(){
    FundlyTheme {
        PercentIndicator(42.0)
    }
}