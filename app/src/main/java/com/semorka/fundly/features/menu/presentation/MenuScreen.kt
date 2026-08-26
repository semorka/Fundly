package com.semorka.fundly.features.menu.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.semorka.fundly.core.data.DatastoreViewModel

@Composable fun MenuScreen(
    dstViewModel: DatastoreViewModel = hiltViewModel()
){
    MenuContent(
        onNewFunds = { funds ->
            dstViewModel.setFunds(funds)
        }
    )
}