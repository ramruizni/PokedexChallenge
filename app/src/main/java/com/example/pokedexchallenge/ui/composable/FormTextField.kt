package com.example.pokedexchallenge.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    errorMsg: String?,
    placeholder: String,
    keyboardType: KeyboardType
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = errorMsg != null,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation =
            if (keyboardType == KeyboardType.Password) PasswordVisualTransformation()
            else VisualTransformation.None
        )
        if (errorMsg != null) {
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}