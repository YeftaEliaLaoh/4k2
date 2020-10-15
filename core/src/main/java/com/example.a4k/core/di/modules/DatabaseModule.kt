package com.example.a4k.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.a4k.core.BuildConfig
import com.example.a4k.core.database.Database
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteDao
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteRepository
import com.example.a4k.core.database.migrations.MIGRATION_1_2
import com.example.a4k.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [Database].
     *
     * @return Instance of database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            BuildConfig.DATABASE_NAME
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao].
     *
     * @return Instance of character favorite data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(database: Database) =
        database.characterFavoriteDao()

    /**
     * Create a provider method binding for [CharacterFavoriteRepository].
     *
     * @return Instance of character favorite repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ) = CharacterFavoriteRepository(characterFavoriteDao)
}
