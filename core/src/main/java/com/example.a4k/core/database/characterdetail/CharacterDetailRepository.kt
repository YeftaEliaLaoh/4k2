package com.example.a4k.core.database.characterdetail

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import javax.inject.Inject

/**
 * Repository module for handling character favorite data operations [CharacterDetailDao].
 */
class CharacterDetailRepository @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val characterDetailDao: CharacterDetailDao
) {

    /**
     * Obtain all database added detail characters ordering by name field.
     *
     * @return [LiveData] List with detail characters.
     */
    fun getAllCharacterDetailLiveData(): LiveData<List<CharacterDetail>> =
        characterDetailDao.getAllCharacterDetailLiveData()

    /**
     * Obtain all database added detail characters ordering by name field.
     *
     * @return List with detail characters.
     */
    suspend fun getAllCharacterDetail(): List<CharacterDetail> =
        characterDetailDao.getAllCharacterDetail()

    /**
     * Obtain database detail character by identifier.
     *
     * @param CharacterDetailId Character identifier.
     *
     * @return detail character if exist, otherwise null
     */
    suspend fun getCharacterDetail(CharacterDetailId: Long): CharacterDetail? =
        characterDetailDao.getCharacterDetail(CharacterDetailId)

    /**
     * Delete all database detail characters.
     */
    suspend fun deleteAllCharacterDetail() =
        characterDetailDao.deleteAllCharacterDetail()

    /**
     * Delete database detail character by identifier.
     *
     * @param CharacterDetailId Character identifier.
     */
    suspend fun deleteCharacterDetailById(CharacterDetailId: Long) =
        characterDetailDao.deleteCharacterDetailById(CharacterDetailId)

    /**
     * Delete database detail character.
     *
     * @param character Character favorite.
     */
    suspend fun deleteCharacterDetail(character: CharacterDetail) =
        characterDetailDao.deleteCharacterDetail(character)

    /**
     * Add to database a list of detail characters.
     *
     * @param characters List of detail characters.
     */
    suspend fun insertCharacterDetails(characters: List<CharacterDetail>) =
        characterDetailDao.insertCharacterDetails(characters)

    /**
     * Add to database a detail character.
     *
     * @param id Character identifier.
     * @param name Character name.
     * @param imageUrl Character thumbnail url.
     */
    suspend fun insertCharacterDetail(id: Long, name: String, email: String, imageUrl: String) {
        val characterDetail = CharacterDetail(
            id = id,
            email = email,
            name = name,
            imageUrl = imageUrl
        )
        characterDetailDao.insertCharacterDetail(characterDetail)
    }
}
