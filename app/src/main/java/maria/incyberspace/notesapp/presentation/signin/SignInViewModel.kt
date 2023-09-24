package maria.incyberspace.notesapp.presentation.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import maria.incyberspace.notesapp.domain.model.Response.Success
import maria.incyberspace.notesapp.domain.model.Response.Loading
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import maria.incyberspace.notesapp.domain.repository.SignInResponse
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(Success(false))
        private set

    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInResponse = Loading
        signInResponse = repo.signIn(email, password)
    }
}