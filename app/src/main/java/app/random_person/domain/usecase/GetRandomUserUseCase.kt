package app.random_person.domain.usecase

import app.random_person.data.model.RandomUser
import app.random_person.domain.repository.RandomUserRepository
import javax.inject.Inject

class GetRandomUserUseCase @Inject constructor(
    private val repository: RandomUserRepository
) {
    suspend operator fun invoke(): RandomUser = repository.getRandomPerson()
}