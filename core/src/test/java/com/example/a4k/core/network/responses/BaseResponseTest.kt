package com.example.a4k.core.network.responses

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Test

class BaseResponseTest {

    @Test
    fun createBaseResponse_ShouldAddCorrectAttributes() {
        val code = 200
        val status = "Ok"
        val message = "Ok"
        val results: List<String> = mock()

        val baseResponse = BaseResponse(
            /*code = code,
            status = status,
            message = message,*/
            results = results
        )

        /*Assert.assertEquals(code, baseResponse.code)
        Assert.assertEquals(status, baseResponse.status)
        Assert.assertEquals(message, baseResponse.message)*/
        Assert.assertEquals(results, baseResponse.results)
    }
}
