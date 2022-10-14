package com.example.pokedexchallenge.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.presentation.destinations.LoginScreenDestination
import com.example.pokedexchallenge.presentation.destinations.PokemonListScreenDestination
import com.example.pokedexchallenge.ui.composable.FormCheckbox
import com.example.pokedexchallenge.ui.composable.FormTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel
            .validationEvents
            .collect { event ->
                when (event) {
                    is LoginViewModel.ValidationEvent.Success -> {
                        navigator.navigate(
                            direction = PokemonListScreenDestination(),
                            onlyIfResumed = true
                        ) {
                            popUpTo(LoginScreenDestination.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokemon_logo), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            FormTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EmailChanged(it))
                },
                errorMsg = state.emailError,
                placeholder = stringResource(R.string.login_email_placeholder),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(24.dp))
            FormTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.PasswordChanged(it))
                },
                errorMsg = state.passwordError,
                placeholder = stringResource(R.string.login_password_placeholder),
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(16.dp))
            FormCheckbox(
                checked = state.acceptedTerms,
                onCheckedChange = {
                    viewModel.onEvent(LoginEvent.AcceptedTerms(it))
                },
                description = stringResource(R.string.login_terms_description),
                errorMsg = state.acceptedTermsError
            )
        }
        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.login_button_submit),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }


        Spacer(modifier = Modifier.height(8.dp))
    }
}