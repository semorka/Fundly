package com.semorka.fundly.features.expense.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semorka.fundly.R
import com.semorka.fundly.core.data.room.Category
import com.semorka.fundly.features.expense.presentation.component.NumberField
import com.semorka.fundly.features.expense.presentation.component.ScheduleMenu
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.features.expense.ExpenseFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseContent(
    onNewExpense: (Double, String, Int?, Category?) -> Unit,
    state: ExpenseFormState,
    onExpenseTextChanged: (String) -> Unit,
    onScheduleToggled: () -> Unit,
    onScheduleTextChanged: (String) -> Unit,
    onCategoryUpdated: (Category) -> Unit
){

    var expanded by remember {mutableStateOf(false)}

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        NumberField(
            value = state.expenseText,
            onValueChange = { newValue ->
                val sanitized = newValue.replace(',', '.')

                if (sanitized.isEmpty() || sanitized.matches(Regex("""^\d*\.?\d{0,2}$"""))) {
                    onExpenseTextChanged(sanitized)
                }
            },
            label = ""
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = {
                onScheduleToggled()
            })
        ) {
            Checkbox(state.scheduleChecked, onCheckedChange = { onScheduleToggled() })
            Text("Schedule expense")
        }

        if (state.scheduleChecked) {
            ScheduleMenu(
                scheduleText = state.scheduleText,
                onScheduleTextChanged = { onScheduleTextChanged(it) }
            )
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded},
        ) {
            OutlinedTextField(
                value = state.selectedCategory?.title ?: "No category",
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = state.selectedCategory?.iconRes ?: R.drawable.ic_menu),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Category.entries.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = category.iconRes),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = category.title)
                            }
                        },
                        onClick = {
                            onCategoryUpdated(category)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }

        Button(
            onClick = {
                onNewExpense(
                    state.expenseText.toDouble(),
                    "expense",
                    if (state.scheduleChecked) state.scheduleText.toInt() else null,
                    state.selectedCategory
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
    FundlyTheme {
        val state = ExpenseFormState(expenseText = "123", scheduleChecked = true)

        ExpenseContent(
            state = state,
            onExpenseTextChanged = {},
            onScheduleToggled = {},
            onScheduleTextChanged = {},
            onCategoryUpdated = {},
            onNewExpense = { _, _, _, _ -> }
        )
    }
}