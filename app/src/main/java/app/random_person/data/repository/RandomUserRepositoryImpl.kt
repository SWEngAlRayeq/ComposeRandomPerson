package app.random_person.data.repository

import app.random_person.data.api.RandomUserApi
import app.random_person.data.model.RandomUser
import app.random_person.domain.repository.RandomUserRepository
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val api: RandomUserApi
) : RandomUserRepository {
    override suspend fun getRandomPerson(): RandomUser {
        return api.getRandomPerson().results.first()
    }
}