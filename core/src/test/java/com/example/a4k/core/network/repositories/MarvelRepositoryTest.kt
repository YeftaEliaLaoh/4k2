package com.example.a4k.core.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.example.a4k.core.BuildConfig
import com.example.a4k.core.network.repositiories.MarvelRepository
import com.example.a4k.core.network.services.MarvelService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC

class MarvelRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var marvelService: MarvelService
    private lateinit var marvelRepository: MarvelRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        marvelRepository = MarvelRepository(marvelService)
    }

    @Test
    fun getCharacters() = runBlocking {
        val charactersOffset = 0
        val charactersLimit = 20
        val (apiKey, hash, timestamp, offset, limit) =
            argumentCaptor<String, String, String, Int, Int>()

        marvelRepository.getCharacters(
            offset = charactersOffset,
            limit = charactersLimit
        )

        verify(marvelService).getCharacters(
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture(),
            offset = offset.capture(),
            limit = limit.capture()
        )

        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertEquals(charactersOffset, offset.lastValue)
        assertEquals(charactersLimit, limit.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }

    @Test
    fun getCharacter() = runBlocking {
        val characterId = 3L
        val (id, apiKey, hash, timestamp) = argumentCaptor<Long, String, String, String>()

        marvelRepository.getCharacter(characterId)

        verify(marvelService).getCharacter(
            id = id.capture(),
            apiKey = apiKey.capture(),
            hash = hash.capture(),
            timestamp = timestamp.capture()
        )

        assertEquals(characterId, id.lastValue)
        assertEquals(API_PUBLIC_KEY, apiKey.lastValue)
        assertNotNull(hash.lastValue)
        assertNotNull(timestamp.lastValue)
    }
}
