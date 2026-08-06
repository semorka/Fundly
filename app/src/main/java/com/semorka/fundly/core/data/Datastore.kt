package com.semorka.fundly.core.data

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semorka.fundly.core.data.DatastoreViewModel.PreferencesKeys.USER_FUNDS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@HiltViewModel
class DatastoreViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    private object PreferencesKeys {
        val USER_FUNDS = doublePreferencesKey("user_funds")
    }

    val userFundsFlow: StateFlow<Double> = application.dataStore.data
        .map { preferences -> preferences[USER_FUNDS] ?: 0.0 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun setFunds(funds: Double) {
        viewModelScope.launch {
            application.dataStore.edit { preferences ->
                preferences[USER_FUNDS] = funds
            }
        }
    }
}