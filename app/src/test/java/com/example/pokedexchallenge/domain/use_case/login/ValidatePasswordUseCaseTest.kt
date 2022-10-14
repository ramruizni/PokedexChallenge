package com.example.pokedexchallenge.domain.use_case.login

import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    private lateinit var validatePassword: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePassword = ValidatePasswordUseCase()
    }

    @Test
    fun `Password with less than 8 characters, returns error`() {
        val result = validatePassword("123")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Password without numbers, returns error`() {
        val result = validatePassword("abcdefgh")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Password without letters, returns error`() {
        val result = validatePassword("12345678")
        assert(!result.isSuccessful)
    }

    @Test
    fun `Password without errors, returns success`() {
        val result = validatePassword("1234abcd")
        assert(result.isSuccessful)
    }
}