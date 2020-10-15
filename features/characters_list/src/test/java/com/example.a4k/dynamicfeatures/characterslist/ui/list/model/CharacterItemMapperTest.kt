package com.example.a4k.dynamicfeatures.characterslist.ui.list.model

import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import com.example.a4k.core.network.responses.CharacterThumbnailResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterItemMapperTest {

    private val mapper = CharacterItemMapper()

    @Test
    fun characterMapper_WithEmptyResults_ShouldReturnEmptyList() = runBlocking {
        val response = BaseResponse(
            code = 200,
            status = "Ok",
            message = "Ok",
            data = DataResponse<CharacterResponse>(
                offset = 0,
                limit = 0,
                total = 0,
                count = 0,
                results = emptyList()
            )
        )

        assertTrue(mapper.map(response).isNullOrEmpty())
    }

    @Test
    fun characterMapper_WithResults_ShouldReturnParsedList() = runBlocking {
        val response = BaseResponse(
            code = 200,
            status = "Ok",
            message = "Ok",
            data = DataResponse(
                offset = 0,
                limit = 0,
                total = 1,
                count = 1,
                results = listOf(
                    CharacterResponse(
                        id = 1011334,
                        name = "3-D Man",
                        description = "",
                        thumbnail = CharacterThumbnailResponse(
                            extension = "jpg"
                        )
                    )
                )
            )
        )

        mapper.map(response).first().run {
            assertEquals(1011334, this.id)
            assertEquals("3-D Man", this.name)
            assertEquals("", this.description)
            assertEquals(
                this.imageUrl
            )
        }
    }
}
