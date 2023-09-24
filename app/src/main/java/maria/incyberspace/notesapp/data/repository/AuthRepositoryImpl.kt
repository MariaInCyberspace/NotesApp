package maria.incyberspace.notesapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import maria.incyberspace.notesapp.domain.model.Response
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth): AuthRepository {

    override val currentUser get() = auth.currentUser

    override suspend fun signUp(email: String, password: String) = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        Response.Success(true)
    } catch (ex: Exception) {
        Response.Failure(ex)
    }

    override suspend fun signIn(email: String, password: String) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Response.Success(true)
    } catch (ex: Exception) {
        Response.Failure(ex)
    }

    override fun signOut() = auth.signOut()

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)
}

