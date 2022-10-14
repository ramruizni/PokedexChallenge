package com.example.pokedexchallenge.presentation.splash

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.commons.Constants.SHARED_PREF_IS_LOGGED_IN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferences: SharedPreferences
) : ViewModel() {

    private val splashEventChannel = Channel<SplashEvent>()
    val splashEvents = splashEventChannel.receiveAsFlow()

    init {
        navigateDependingOnSession()
    }

    private fun navigateDependingOnSession() {
        val isLoggedIn = preferences.getBoolean(SHARED_PREF_IS_LOGGED_IN, false)
        viewModelScope.launch {
            splashEventChannel.send(
                if (isLoggedIn) SplashEvent.NavigateToList else SplashEvent.NavigateToLogin
            )
        }
    }

    sealed class SplashEvent {
        object NavigateToLogin : SplashEvent()
        object NavigateToList : SplashEvent()
    }
}