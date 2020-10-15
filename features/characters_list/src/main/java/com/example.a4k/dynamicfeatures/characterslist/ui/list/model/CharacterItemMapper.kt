package com.example.a4k.dynamicfeatures.characterslist.ui.list.model

import com.example.a4k.core.mapper.Mapper
import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import kotlin.random.Random

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
open class CharacterItemMapper : Mapper<BaseResponse<CharacterResponse>, List<CharacterItem>> {

    /**
     * Transform network response to [CharacterItem].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     */
    override suspend fun map(from: BaseResponse<CharacterResponse>) =
        from.results.map {
            CharacterItem(
                id = Random.nextLong(),
                username = it.login.username,
                email = it.email,
                imageUrl = it.picture.large
                )
        }
}
