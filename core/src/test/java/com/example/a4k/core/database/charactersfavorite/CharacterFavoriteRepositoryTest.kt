package com.example.a4k.core.database.charactersfavorite

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.example.a4k.core.database.characterfavorite.CharacterFavorite
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteDao
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterFavoriteRepositoryTest {

    @Mock
    lateinit var characterFavoriteDao: CharacterFavoriteDao
    lateinit var characterFavoriteRepository: CharacterFavoriteRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        characterFavoriteRepository = CharacterFavoriteRepository(characterFavoriteDao)
    }

    @Test
    fun getAllCharactersFavoriteLiveData_ShouldInvokeCorrectDaoMethod() {
        characterFavoriteRepository.getAllCharactersFavoriteLiveData()

        verify(characterFavoriteDao).getAllCharactersFavoriteLiveData()
    }

    @Test
    fun getAllCharactersFavorite_ShouldInvokeCorrectDaoMethod() {
        runBlocking {
            characterFavoriteRepository.getAllCharactersFavorite()

            verify(characterFavoriteDao).getAllCharactersFavorite()
        }
    }

    @Test
    fun getCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterIdToFind = "1"
        val characterIdCaptor = argumentCaptor<String>()
        characterFavoriteRepository.getCharacterFavorite(characterIdToFind)

        verify(characterFavoriteDao).getCharacterFavorite(characterIdCaptor.capture())
        assertEquals(characterIdToFind, characterIdCaptor.lastValue)
    }

    @Test
    fun deleteAllCharactersFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        characterFavoriteRepository.deleteAllCharactersFavorite()

        verify(characterFavoriteDao).deleteAllCharactersFavorite()
    }

    @Test
    fun deleteCharacterFavoriteById_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterIdToDelete = "1"
        val characterIdCaptor = argumentCaptor<String>()
        characterFavoriteRepository.deleteCharacterFavoriteById(characterIdToDelete)

        verify(characterFavoriteDao).deleteCharacterFavoriteById(characterIdCaptor.capture())
        assertEquals(characterIdToDelete, characterIdCaptor.lastValue)
    }

    @Test
    fun deleteCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterToDelete = CharacterFavorite(
            0,
            "A.I.M",
            "http://i.annihil.us/535fecbbb9784.jpg"
        )
        val characterFavoriteCaptor = argumentCaptor<CharacterFavorite>()
        characterFavoriteRepository.deleteCharacterFavorite(characterToDelete)

        verify(characterFavoriteDao).deleteCharacterFavorite(characterFavoriteCaptor.capture())
        assertEquals(characterToDelete, characterFavoriteCaptor.lastValue)
    }

    @Test
    fun insertCharactersFavorites_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val charactersToInsert = listOf(
            CharacterFavorite(0, "3-D Man", "http://i.annihil.us/535fecbbb9784.jpg"),
            CharacterFavorite(1, "A-Bomb (HAS)", "http://i.annihil.us/5232158de5b16.jpg"),
            CharacterFavorite(2, "A.I.M", "http://i.annihil.us/52602f21f29ec.jpg")
        )
        val charactersInsertedCaptor = argumentCaptor<List<CharacterFavorite>>()
        characterFavoriteRepository.insertCharactersFavorites(charactersToInsert)

        verify(characterFavoriteDao).insertCharactersFavorites(charactersInsertedCaptor.capture())
        assertEquals(charactersToInsert, charactersInsertedCaptor.lastValue)
    }

    @Test
    fun insertCharacterFavorite_ShouldInvokeCorrectDaoMethod() = runBlocking {
        val characterToInsert = CharacterFavorite(
            0,
            "A.I.M",
            "http://i.annihil.us/535fecbbb9784.jpg"
        )
        val characterInsertedCaptor = argumentCaptor<CharacterFavorite>()
        characterFavoriteRepository.insertCharacterFavorite(
            id = characterToInsert.id,
            name = characterToInsert.name,
            imageUrl = characterToInsert.imageUrl
        )

        verify(characterFavoriteDao).insertCharacterFavorite(characterInsertedCaptor.capture())
        assertEquals(characterToInsert, characterInsertedCaptor.lastValue)
    }
}
