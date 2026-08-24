package com.semorka.fundly.core.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semorka.fundly.core.data.room.Category
import com.semorka.fundly.core.data.room.ExpenseEntity
import com.semorka.fundly.core.features.home.ExpenseCard
import com.semorka.fundly.core.features.home.PercentIndicator
import com.semorka.fundly.core.ui.DefaultText
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.core.ui.theme.NeutralSurfaceWhite
import com.semorka.fundly.core.ui.theme.TeachersFontFamily
import com.semorka.fundly.core.utils.formatToTwoDecimals
import com.semorka.fundly.core.utils.trimZeroDecimal

@Composable
fun HomeContent(
    funds: Double,
    oneTimeExpenses: List<ExpenseEntity>,
    scheduledExpenses: List<ExpenseEntity>,
    getScheduledAmount: (ExpenseEntity) -> Double,
    totalExpenses: Double,
    maxOneTimeExpenses: Int = 5
) {
    val spentPercent = remember(totalExpenses, funds) {
        if (funds > 0) {
            (totalExpenses * 100) / funds
        } else 0.0
    }

    val fundsLeft = remember(funds, totalExpenses) {
        funds - totalExpenses
    }

    val displayedOneTimeExpenses = remember(oneTimeExpenses, maxOneTimeExpenses) {
        oneTimeExpenses.takeLast(maxOneTimeExpenses)
    }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (funds > 0) {
                Card(
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp)
                    ) {
                        PercentIndicator(
                            percent = spentPercent,
                            modifier = Modifier.size(125.dp)
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                DefaultText(
                                    text = "Remaining Funds",
                                    fontSize = 16
                                )
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    if (fundsLeft < 1000) {
                                        DefaultText(
                                            text = "$${fundsLeft.formatToTwoDecimals()}",
                                            fontSize = 30,
                                            fontFamily = TeachersFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.alignByBaseline()
                                        )
                                        DefaultText(
                                            text = "left",
                                            fontSize = 20,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.alignByBaseline()
                                        )
                                    } else {
                                        DefaultText(
                                            text = "$${fundsLeft.toInt()}",
                                            fontSize = 30,
                                            fontFamily = TeachersFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.alignByBaseline()
                                        )
                                        DefaultText(
                                            text = "left",
                                            fontSize = 20,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.alignByBaseline()
                                        )
                                    }
                                }
                            }

                            Column(
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                DefaultText(
                                    text = "Total Spent",
                                    fontSize = 16,
                                    color = Color.Gray
                                )
                                DefaultText(
                                    text = "$${totalExpenses.formatToTwoDecimals()} spent",
                                    fontSize = 19,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            displayedOneTimeExpenses.forEach { expense ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ExpenseCard(expense)
                }
            }

            if (scheduledExpenses.isNotEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(vertical = 8.dp)
                ) {
                    DefaultText("Scheduled", fontSize = 20)
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        20.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(scheduledExpenses) { expense ->
                        Card(
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                            modifier = Modifier.height(160.dp)
                        ) {
                            DefaultText(
                                "Every ${expense.schedule} days",
                                fontSize = 20,
                                modifier = Modifier.background(MaterialTheme.colorScheme.primary).padding(6.dp)
                            )
                            DefaultText(expense.name, fontSize = 25, fontWeight = FontWeight.Medium)

                            Spacer(modifier = Modifier.weight(1f))

                            DefaultText(
                                "-$${expense.cost.trimZeroDecimal()}",
                                fontSize = 25,
                                fontWeight = FontWeight.Medium
                            )
                            DefaultText(
                                "Total $${getScheduledAmount(expense)}\nsince created"
                            )
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
    FundlyTheme(darkTheme = true) {
        val oneTimeExpenses = List(16) { index ->
            ExpenseEntity(
                uid = index,
                cost = 99.5,
                name = "Pizza",
                schedule = null,
                category = Category.FOOD
            )
        }

        val scheduledExpenses = List(4) { index ->
            ExpenseEntity(
                uid = index,
                cost = 99.5,
                name = "Pizza",
                schedule = index,
                category = Category.FOOD
            )
        }

        HomeContent(
            funds = 19999.99,
            oneTimeExpenses = oneTimeExpenses,
            scheduledExpenses = scheduledExpenses,
            getScheduledAmount = { _ -> 20.0 },
            totalExpenses = 10000.0
        )
    }
}