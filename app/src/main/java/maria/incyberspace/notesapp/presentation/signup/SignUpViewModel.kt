package maria.incyberspace.notesapp.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import maria.incyberspace.notesapp.domain.model.Response
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import maria.incyberspace.notesapp.domain.repository.SignUpResponse
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set


    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = repo.signUp(email, password)
    }

}