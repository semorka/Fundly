package com.semorka.fundly.features.expense.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.semorka.fundly.R
import com.semorka.fundly.features.expense.ScheduleInterval

@Composable
fun ScheduleMenu(
    modifier: Modifier = Modifier,
    scheduleText: String,
    onScheduleTextChanged: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
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
                ScheduleInterval.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.title) },
                        {
                            onScheduleTextChanged(option.daysCount.toString())
                            expanded = false
                        }
                    )
                }
            }
        }
        NumberField(
            value = scheduleText,
            onValueChange = { newText ->
                if (newText.all { it.isDigit() }) {
                    onScheduleTextChanged(newText)
                }
            },
            label = "Every N days"
        )
    }
}