package com.semorka.fundly.features.home.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.semorka.fundly.core.ScreenPreview

@Composable fun HomeScreen(){
    Text("Fundle")
}

@Preview
@Composable private fun HomeScreenPreview() = ScreenPreview( { HomeScreen() })