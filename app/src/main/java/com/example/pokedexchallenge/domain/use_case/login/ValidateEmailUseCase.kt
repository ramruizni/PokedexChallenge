package com.example.pokedexchallenge.domain.use_case.login

import com.example.pokedexchallenge.commons.Matchers


class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "The email can't be blank"
            )
        }

        if (!Matchers.isValidEmail(email)) {
            return ValidationResult(
                isSuccessful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

}