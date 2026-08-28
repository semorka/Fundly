package com.semorka.fundly.features.expense.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semorka.fundly.R
import com.semorka.fundly.core.data.room.Category
import com.semorka.fundly.features.expense.presentation.component.NumberField
import com.semorka.fundly.features.expense.presentation.component.ScheduleMenu
import com.semorka.fundly.core.ui.theme.FundlyTheme
import com.semorka.fundly.features.expense.domain.ExpenseFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseContent(
    onNewExpense: () -> Unit,
    state: ExpenseFormState,
    onExpenseNameChanged: (String) -> Unit,
    onExpenseTextChanged: (String) -> Unit,
    onScheduleToggled: () -> Unit,
    onScheduleTextChanged: (String) -> Unit,
    onCategoryUpdated: (Category?) -> Unit
){
    var expanded by remember {mutableStateOf(false)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = state.expenseName,
                label = { Text("Add note or description...", color = MaterialTheme.colorScheme.onBackground) },
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null) },
                onValueChange = { onExpenseNameChanged(it) },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 20.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

            NumberField(
                value = state.expenseText,
                onValueChange = { newValue ->
                    val sanitized = newValue.replace(',', '.')

                    if (sanitized.isEmpty() || sanitized.matches(Regex("""^\d*\.?\d{0,2}$"""))) {
                        onExpenseTextChanged(sanitized)
                    }
                },
                placeholder = "0.00",
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded},
            ) {
                OutlinedTextField(
                    value = state.selectedCategory?.title ?: "Select category",
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = state.selectedCategory?.iconRes ?: R.drawable.ic_folder),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
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
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_menu),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "No category")
                            }
                        },
                        onClick = {
                            onCategoryUpdated(null)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )

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

            OutlinedCard {
                Column(Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("Schedule expense", fontSize = 20.sp)
                        Switch(
                            checked = state.scheduleChecked,
                            onCheckedChange = { onScheduleToggled() })
                    }
                    if (state.scheduleChecked) {
                        ScheduleMenu(
                            scheduleText = state.scheduleText,
                            onScheduleTextChanged = { onScheduleTextChanged(it) },
                            modifier = Modifier
                        )
                    }
                }

            }
        }
        Button(
            onClick = {
                onNewExpense()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add expense", fontSize = 24.sp)
            Spacer(Modifier.size(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ExpenseContentPreview() {
    FundlyTheme {
        val state = ExpenseFormState(expenseText = "123", scheduleChecked = true, expenseName = "default")

        ExpenseContent(
            onNewExpense = { },
            state = state,
            onExpenseNameChanged = {},
            onExpenseTextChanged = {},
            onScheduleToggled = {},
            onScheduleTextChanged = {},
            onCategoryUpdated = {}
        )
    }
}