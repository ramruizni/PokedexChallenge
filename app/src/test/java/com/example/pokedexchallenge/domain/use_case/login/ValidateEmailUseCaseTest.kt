package com.example.pokedexchallenge.domain.use_case.login

import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseTest {

    private lateinit var validateEmail: ValidateEmailUseCase

    @Before
    fun setUp() {
        validateEmail = ValidateEmailUseCase()
    }

    @Test
    fun `Email blank, returns error`() {
        val result = validateEmail("")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Email that doesn't match the pattern, returns error`() {
        val result = validateEmail("email@address@.com")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Email matching the pattern, returns success`() {
        val result = validateEmail("email@address.com")
        assert(result.isSuccessful)
    }
}