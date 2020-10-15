package com.example.a4k.core.di

import android.content.Context
import com.example.a4k.core.database.Database
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteDao
import com.example.a4k.core.di.modules.DatabaseModule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DatabaseModuleTest {

    private lateinit var databaseModule: DatabaseModule

    @Before
    fun setUp() {
        databaseModule = DatabaseModule()
    }

    @Test
    fun verifyProvidedDatabase() {
        val context: Context = mock()
        val database = databaseModule.provideDatabase(context)

        assertNotNull(database.characterFavoriteDao())
    }

    @Test
    fun verifyProvidedCharacterFavoriteDao() {
        val database: Database = mock()
        val characterFavoriteDao: CharacterFavoriteDao = mock()

        doReturn(characterFavoriteDao).whenever(database).characterFavoriteDao()

        assertEquals(
            characterFavoriteDao,
            databaseModule.provideCharacterFavoriteDao(database)
        )
        verify(database).characterFavoriteDao()
    }

    @Test
    fun verifyProvidedCharacterFavoriteRepository() {
        val characterFavoriteDao: CharacterFavoriteDao = mock()
        val repository = databaseModule.provideCharacterFavoriteRepository(characterFavoriteDao)

        assertEquals(characterFavoriteDao, repository.characterFavoriteDao)
    }
}
