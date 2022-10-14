package com.example.pokedexchallenge.presentation.login

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Constants.SHARED_PREF_IS_LOGGED_IN
import com.example.pokedexchallenge.domain.use_case.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val app: Application,
    private val useCases: LoginUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email,
                    emailError = null
                )
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    passwordError = null
                )
            }
            is LoginEvent.AcceptedTerms -> {
                state = state.copy(
                    acceptedTerms = event.acceptedTerms,
                    acceptedTermsError = null
                )
            }
            is LoginEvent.Submit -> {
                submitForm()
            }
        }
    }

    private fun submitForm() {
        val emailResult = useCases.validateEmail(state.email)
        val passwordResult = useCases.validatePassword(state.password)
        val termsResult = useCases.validateTerms(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            termsResult
        ).any { !it.isSuccessful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                acceptedTermsError = termsResult.errorMessage
            )
            return
        }

        preferences.edit().putBoolean(SHARED_PREF_IS_LOGGED_IN, true).apply()

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }

        Toasty.info(
            app,
            app.getString(R.string.toast_login_successful),
            Toast.LENGTH_SHORT
        ).show()
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}