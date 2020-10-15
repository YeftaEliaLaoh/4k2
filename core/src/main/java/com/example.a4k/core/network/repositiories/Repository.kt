package com.example.a4k.core.network.repositiories

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import com.example.a4k.core.network.services.Service

/**
 * Repository module for handling API network operations [Service].
 */
class Repository(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val service: Service
) {

    /**
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set to the specified number of resources.
     * @return Response for comic characters resource.
     */
    suspend fun getCharacters(page: Int, results: Int): BaseResponse<CharacterResponse> {
        return service.getCharacters(
            page = page,
            results = results
        )
    }

}
