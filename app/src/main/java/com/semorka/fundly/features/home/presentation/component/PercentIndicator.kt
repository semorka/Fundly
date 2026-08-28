package com.semorka.fundly.features.home.presentation.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.semorka.fundly.core.ui.DefaultText
import com.semorka.fundly.core.ui.theme.FundlyTheme

@Composable
fun PercentIndicator(
    percent: Double,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier.aspectRatio(1f)
    ) {
        val minDimension = minOf(maxWidth, maxHeight)

        val fontSize = (minDimension.value * 0.25f)

        val strokeWidth = minDimension * 0.08f

        DefaultText(
            text = "${percent.toInt()}%",
            fontSize = fontSize.toInt()
        )
        CircularProgressIndicator(
            progress = { (percent / 100).toFloat() },
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF9ED6C6),
            trackColor = Color(0xFFE3DCD0),
            strokeWidth = strokeWidth,
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