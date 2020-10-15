package com.example.a4k.core.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.example.a4k.core.BuildConfig
import com.example.a4k.core.network.repositiories.Repository
import com.example.a4k.core.network.services.Service
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: Service
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(service)
    }

    @Test
    fun getCharacters() = runBlocking {
        val charactersOffset = 1
        val charactersLimit = 20
        val (page, results) =
            argumentCaptor<Int, Int>()

        repository.getCharacters(
            page = charactersOffset,
            results = charactersLimit
        )

        verify(service).getCharacters(
            page = page.capture(),
            results = results.capture()
        )

        assertEquals(charactersOffset, page.lastValue)
        assertEquals(charactersLimit, results.lastValue)

    }
}
