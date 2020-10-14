package com.example.a4k.core.di.modules

import com.example.a4k.core.BuildConfig
import com.example.a4k.core.di.CoreComponent
import com.example.a4k.core.network.repositiories.MarvelRepository
import com.example.a4k.core.network.services.MarvelService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class NetworkModule {

    /**
     * Create a provider method binding for [HttpLoggingInterceptor].
     *
     * @return Instance of http interceptor.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Create a provider method binding for [OkHttpClient].
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Create a provider method binding for [Retrofit].
     *
     * @return Instance of retrofit.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder() =
        Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Create a provider method binding for [MarvelService].
     *
     * @return Instance of marvel service.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelService(retrofit: Retrofit) = retrofit.create(MarvelService::class.java)

    /**
     * Create a provider method binding for [MarvelRepository].
     *
     * @return Instance of marvel repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelRepository(service: MarvelService) = MarvelRepository(service)
}
