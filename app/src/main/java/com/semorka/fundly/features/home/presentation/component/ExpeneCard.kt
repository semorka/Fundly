package com.semorka.fundly.features.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semorka.fundly.core.data.room.Category
import com.semorka.fundly.core.data.room.ExpenseEntity
import com.semorka.fundly.core.ui.DefaultText
import com.semorka.fundly.core.ui.theme.FoodCategoryBackground
import com.semorka.fundly.core.ui.theme.FoodCategoryIcon
import com.semorka.fundly.core.utils.formatDate
import com.semorka.fundly.core.utils.trimZeroDecimal

@Composable fun ExpenseCard(expense: ExpenseEntity){
    val date = formatDate(expense.timestamp)
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            expense.category?.let { category ->
                Icon(
                    painter = painterResource(id = category.iconRes),
                    modifier = Modifier
                        .size(60.dp)
                        .background(FoodCategoryBackground, CircleShape)
                        .padding(8.dp),
                    contentDescription = null,
                    tint = FoodCategoryIcon
                )
            }
            Column(
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                DefaultText(expense.name, fontSize = 23, fontWeight = FontWeight.Medium)
                DefaultText(date, fontSize = 16, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            DefaultText(
                "-$${expense.cost.trimZeroDecimal()}",
                fontSize = 25,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable private fun ExpenseCardPreview() {
    val expense = ExpenseEntity(
        uid = 0,
        cost = 99.5,
        name = "Pizza",
        schedule = null,
        category = Category.FOOD
    )
    ExpenseCard(expense)
}