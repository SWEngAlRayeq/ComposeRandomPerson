package app.random_person.domain.repository

import app.random_person.data.model.RandomUser

interface RandomUserRepository {
    suspend fun getRandomPerson(): RandomUser
}