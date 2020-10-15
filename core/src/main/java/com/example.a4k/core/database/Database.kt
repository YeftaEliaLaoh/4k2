package com.example.a4k.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a4k.core.BuildConfig
import com.example.a4k.core.database.characterfavorite.CharacterFavorite
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteDao

/**
 * room database storing the different requested information
 *
 * @see Database
 */
@Database(
    entities = [CharacterFavorite::class],
    exportSchema = BuildConfig.DATABASE_EXPORT_SCHEMA,
    version = BuildConfig.DATABASE_VERSION
)
abstract class Database : RoomDatabase() {

    /**
     * Get character favorite data access object.
     *
     * @return Character favorite dao.
     */
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
}
