package com.example.a4k.core.database.characterdetail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/**
 * The data access object for the [CharacterDetail] class.
 *
 * @see Dao
 */
@Dao
interface CharacterDetailDao {

    /**
     * Obtain all database added detail characters ordering by name field.
     *
     * @return [LiveData] List with detail characters.
     */
    @Query("SELECT * FROM character_detail ORDER BY name")
    fun getAllCharacterDetailLiveData(): LiveData<List<CharacterDetail>>

    /**
     * Obtain all database added detail characters ordering by name field.
     *
     * @return List with detail characters.
     */
    @Query("SELECT * FROM character_detail ORDER BY name")
    suspend fun getAllCharacterDetail(): List<CharacterDetail>

    /**
     * Obtain database detail character by identifier.
     *
     * @param CharacterDetailId Character identifier.
     *
     * @return detail character if exist, otherwise null.
     */
    @Query("SELECT * FROM character_detail WHERE id = :CharacterDetailId")
    suspend fun getCharacterDetail(CharacterDetailId: Long): CharacterDetail?

    /**
     * Delete all database detail characters.
     */
    @Query("DELETE FROM character_detail")
    suspend fun deleteAllCharacterDetail()

    /**
     * Delete database detail character by identifier.
     *
     * @param CharacterDetailId Character identifier.
     */
    @Query("DELETE FROM character_detail WHERE id = :CharacterDetailId")
    suspend fun deleteCharacterDetailById(CharacterDetailId: Long)

    /**
     * Delete database detail character.
     *
     * @param character Character favorite.
     */
    @Delete
    suspend fun deleteCharacterDetail(character: CharacterDetail)

    /**
     * Add to database a list of detail characters.
     *
     * @param characters List of detail characters.
     */
    @Insert
    suspend fun insertCharacterDetails(characters: List<CharacterDetail>)

    /**
     * Add to database a detail character.
     *
     * @param character detail character.
     */
    @Insert
    suspend fun insertCharacterDetail(character: CharacterDetail)
}
