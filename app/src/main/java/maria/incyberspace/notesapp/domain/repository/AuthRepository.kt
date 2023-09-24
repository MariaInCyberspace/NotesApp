package maria.incyberspace.notesapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import maria.incyberspace.notesapp.domain.model.Response

typealias SignUpResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun signUp(email: String, password: String): SignUpResponse

    suspend fun signIn(email: String, password: String): SignInResponse

    fun signOut()

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
}