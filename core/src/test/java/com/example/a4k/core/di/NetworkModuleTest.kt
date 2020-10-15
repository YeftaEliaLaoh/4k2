package com.example.a4k.core.di

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.example.a4k.core.BuildConfig
import com.example.a4k.core.di.modules.NetworkModule
import com.example.a4k.core.network.services.Service
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mock<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()

        assertEquals(BuildConfig.API_BASE_URL, retrofit.baseUrl().toUrl().toString())
    }

    @Test
    fun verifyProvidedService() {
        val retrofit = mock<Retrofit>()
        val service = mock<Service>()
        val serviceClassCaptor = argumentCaptor<Class<*>>()

        doReturn(service).whenever(retrofit).create<Service>(any())

        networkModule.provideService(retrofit)

        verify(retrofit).create(serviceClassCaptor.capture())
        assertEquals(Service::class.java, serviceClassCaptor.lastValue)
    }

    @Test
    fun verifyProvidedRepository() {
        val service = mock<Service>()
        val repository = networkModule.provideRepository(service)

        assertEquals(service, repository.service)
    }
}
