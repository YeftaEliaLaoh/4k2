package com.example.a4k.core.network.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a4k.core.network.responses.BaseResponse
import com.example.a4k.core.network.responses.CharacterResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

object MockResponses {
    object GetCharacters {
        const val STATUS_200 = "mock-responses/get-characters-status200.json"
        const val STATUS_204 = "mock-responses/get-characters-status204.json"
        const val STATUS_401 = "mock-responses/get-characters-status401.json"
    }

    object GetCharacterId {
        const val STATUS_200 = "mock-responses/get-character-id-status200.json"
        const val STATUS_401 = "mock-responses/get-character-id-status401.json"
        const val STATUS_404 = "mock-responses/get-character-id-status404.json"
    }
}

class ServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var service: Service
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestGetCharacters() = runBlocking {
        enqueueResponse(MockResponses.GetCharacters.STATUS_200)

        val page = 1
        val results = 10
        service.getCharacters(
            page = page,
            results = results
        )

        val request = mockWebServer.takeRequest()
        assertEquals("GET", request.method)
        assertEquals(
            "/?page=$page&results=$results",
            request.path
        )
    }

    private fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource!!.readString(Charsets.UTF_8)
            )
        )
    }
}
