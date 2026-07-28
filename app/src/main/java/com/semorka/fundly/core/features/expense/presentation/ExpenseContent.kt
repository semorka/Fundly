package com.semorka.fundly.core.features.expense.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.semorka.fundly.R
import com.semorka.fundly.core.ui.theme.FundlyTheme

@Composable
fun ExpenseContent(
    onNewExpense: (Double, String, Int?) -> Unit
){
    var expenseText by remember { mutableStateOf("") }
    var scheduleText by remember { mutableStateOf("") }

    var scheduleChecked by remember { mutableStateOf(false) }

    val options = listOf("Every day", "Every week", "Every month")
    val optionKeys = listOf(1, 4, 30)
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = expenseText,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    expenseText = newValue
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = {
                scheduleChecked = !scheduleChecked
            })
        ) {
            Checkbox(scheduleChecked, onCheckedChange = { scheduleChecked = !scheduleChecked })
            Text("Schedule expense")
        }

        if (scheduleChecked) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                Box(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Icon(
                            painterResource(R.drawable.ic_expense),
                            contentDescription = "options",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = !expanded }
                    ) {
                        options.forEachIndexed { index, option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                {
                                    selectedOptionText = option
                                    scheduleText = optionKeys[index].toString()
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                TextField(
                    value = scheduleText,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            scheduleText = newValue
                        }
                    },
                    label = { Text("Every N days") }
                )
            }
        }

        Button(
            onClick = {
                onNewExpense(
                    expenseText.toDouble(),
                    "expense",
                    if (scheduleChecked) scheduleText.toInt() else null
                )
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Add expense")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ExpenseContentPreview() {
    FundlyTheme{
        ExpenseContent{_, _, _ ->}
    }
}