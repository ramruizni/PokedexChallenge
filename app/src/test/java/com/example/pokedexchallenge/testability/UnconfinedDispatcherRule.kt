package com.example.pokedexchallenge.testability

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A [TestRule] that initializes the main dispatcher to [dispatcher], which defaults to a
 * [StandardTestDispatcher].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class UnconfinedDispatcherRule(
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}