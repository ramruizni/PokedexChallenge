package com.example.pokedexchallenge.commons

import java.util.regex.Pattern

object Matchers {
    private const val EMAIL_PATTERN =
        "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

    fun isValidEmail(email: String) = Pattern.compile(EMAIL_PATTERN).matcher(email).matches()
}