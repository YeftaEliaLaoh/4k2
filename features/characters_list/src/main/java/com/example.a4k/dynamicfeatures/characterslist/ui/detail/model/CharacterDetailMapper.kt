package com.example.a4k.dynamicfeatures.characterslist.ui.detail.model

import com.example.a4k.core.mapper.Mapper
import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import kotlin.random.Random

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class CharacterDetailMapper : Mapper<BaseResponse<CharacterResponse>, CharacterDetail> {

    /**
     * Transform network response to [CharacterDetail].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     * @throws NoSuchElementException If the response results are empty.
     */
    @Throws(NoSuchElementException::class)
    override suspend fun map(from: BaseResponse<CharacterResponse>): CharacterDetail {
        val characterResponse = from.results.first()
        return CharacterDetail(
            id = Random.nextLong(),
            username = characterResponse.login.username,
            email = characterResponse.email,
            imageUrl = characterResponse.picture.large
        )
    }
}
