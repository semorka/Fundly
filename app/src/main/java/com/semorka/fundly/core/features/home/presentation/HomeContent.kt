package com.semorka.fundly.core.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semorka.fundly.core.data.room.ExpenseEntity
import com.semorka.fundly.core.features.home.PercentIndicator
import com.semorka.fundly.core.ui.DefaultText
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.core.ui.theme.NeutralSurfaceWhite
import com.semorka.fundly.core.ui.theme.NeutralWarmBackground
import com.semorka.fundly.core.ui.theme.Pink40
import com.semorka.fundly.core.ui.theme.TeachersFontFamily
import com.semorka.fundly.core.utils.formatDate
import com.semorka.fundly.core.utils.ignoreHeight
import com.semorka.fundly.core.utils.trimZeroDecimal

@Composable
fun HomeContent(
    funds: Int,
    oneTimeExpenses: List<ExpenseEntity>,
    scheduledExpenses: List<ExpenseEntity>,
    getScheduledAmount : (ExpenseEntity) -> Double,
    totalExpenses: Double
) {
    val spentPercent = remember(totalExpenses, funds) {
        if (funds > 0) {
            (totalExpenses * 100) / funds
        } else 0.0
    }

    val fundsLeft = remember(funds, totalExpenses) {
        funds - totalExpenses
    }

    Surface(
        color = NeutralWarmBackground,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp)
        ) {
            if (funds > 0) {
                Card(
                    elevation = CardDefaults.cardElevation(16.dp),
                    modifier = Modifier.weight(1f).padding(bottom=16.dp)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            DefaultText("${spentPercent.toInt()}%", fontSize = 30)
                            CircularProgressIndicator(
                                progress = { (spentPercent / 100).toFloat() },
                                modifier = Modifier.size(100.dp),
                                trackColor = Color(0xFFEFECE6),
                                strokeWidth = 12.dp,
                                strokeCap = StrokeCap.Round
                            )
                        }
//                        PercentIndicator(
//                            spentPercent
//                        )
                        Column(
                            Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            DefaultText(
                                "${totalExpenses.trimZeroDecimal()} spent", fontSize = 38, fontFamily = TeachersFontFamily
                            )
                            DefaultText(
                                "${fundsLeft.trimZeroDecimal()} left", fontSize = 28, fontFamily = TeachersFontFamily
                            )
                        }
                    }
                }
            }
            Column(
                Modifier.weight(5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier.weight(3f)
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = oneTimeExpenses,
                            key = { it.uid }
                        ) { expense ->
                            val date = formatDate(expense.timestamp)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    20.dp,
                                    Alignment.CenterHorizontally
                                ), modifier = Modifier.fillMaxWidth()
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(NeutralSurfaceWhite),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            DefaultText(expense.cost.trimZeroDecimal(), fontSize = 25)
                                            DefaultText(expense.name, fontSize = 20)
                                        }
                                        DefaultText(date, fontSize = 18)
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    Modifier.weight(2f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        DefaultText("Scheduled", fontSize = 20)
                    }
                    scheduledExpenses.forEach { expense ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            DefaultText(expense.cost.trimZeroDecimal(), fontSize = 25)
                            DefaultText(expense.name, fontSize = 25)
                            DefaultText("Every ${expense.schedule} days", fontSize = 20)
                            DefaultText("Total ${getScheduledAmount(expense)}", fontSize = 14)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    FundlyTheme {
        val oneTimeExpenses = List(16) { index ->
            ExpenseEntity(
                uid = index,
                cost = 99.5,
                name = "new gaming keyboard",
                schedule = null
            )
        }

        HomeContent(
            funds = 1000,
            oneTimeExpenses = oneTimeExpenses,
            scheduledExpenses = listOf(
                ExpenseEntity(
                    uid = 99999,
                    cost = 20.0,
                    name = "Transport",
                    schedule = 1
                )
            ),
            getScheduledAmount = { _ -> 20.0 },
            totalExpenses = 100.0
        )
    }
}