package com.semorka.fundly.core.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semorka.fundly.core.ui.DefaultText
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.core.ui.theme.MadimiFontFamily
import com.semorka.fundly.core.utils.trimZeroDecimal

@Composable
fun PercentIndicator(
    percent: Double,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        DefaultText(
            "${percent.toInt()}%",
            fontSize = 32
        )
        CircularProgressIndicator(
            progress = { (percent / 100).toFloat() },
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF9ED6C6),
            trackColor = Color(0xFFE3DCD0),
            strokeWidth = 16.dp,
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview
@Composable private fun PercentPreview(){
    FundlyTheme {
        PercentIndicator(42.0)
    }
}