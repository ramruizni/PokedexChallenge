package com.example.pokedexchallenge.domain.use_case.login

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "The password must have at least 8 characters"
            )
        }
        val containsLetterAndDigit = password.any { it.isLetter() } && password.any { it.isDigit() }
        if (!containsLetterAndDigit) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "The password needs to contain at least one letter and one digit"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

}