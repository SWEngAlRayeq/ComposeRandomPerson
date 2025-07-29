package app.random_person.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.random_person.data.model.RandomUser
import app.random_person.data.model.RandomUserResponse
import app.random_person.domain.usecase.GetRandomUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(
    private val useCase: GetRandomUserUseCase
) : ViewModel() {

    var user by mutableStateOf<RandomUser?>(null)
        private set

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    init {
        fetchRandomUser()
    }

    fun fetchRandomUser() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                user = useCase()
            } catch (e: Exception) {
                error = e.localizedMessage ?: "Unknown error"
            }
            isLoading = false
        }
    }


}