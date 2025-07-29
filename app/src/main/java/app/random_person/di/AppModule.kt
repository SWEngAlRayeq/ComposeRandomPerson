package app.random_person.di

import app.random_person.data.api.RandomUserApi
import app.random_person.data.repository.RandomUserRepositoryImpl
import app.random_person.domain.repository.RandomUserRepository
import app.random_person.domain.usecase.GetRandomUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.apply
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideChuckApi(client: OkHttpClient): RandomUserApi =
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RandomUserApi::class.java)

    @Provides
    @Singleton
    fun provideChuckRepository(api: RandomUserApi): RandomUserRepository =
        RandomUserRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideChuckUseCase(repo: RandomUserRepository): GetRandomUserUseCase =
        GetRandomUserUseCase(repo)

}