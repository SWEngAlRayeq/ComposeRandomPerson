package app.random_person.data.api

import app.random_person.data.model.RandomUserResponse
import retrofit2.http.GET

interface RandomUserApi {

    @GET("api/")
    suspend fun getRandomPerson(): RandomUserResponse

}