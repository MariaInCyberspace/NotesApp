package maria.incyberspace.notesapp.dependencyinjection

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import maria.incyberspace.notesapp.core.Constants.Constants.NOTES
import maria.incyberspace.notesapp.data.repository.AuthRepositoryImpl
import maria.incyberspace.notesapp.data.repository.NotesRepositoryImpl
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import maria.incyberspace.notesapp.domain.repository.NotesRepository

@Module
@InstallIn(SingletonComponent::class)
class AppMod {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(auth = Firebase.auth)

    @Provides
    fun provideNotesRef() = Firebase.firestore.collection(NOTES)

    @Provides
    fun provideNotesRepository(notesRef: CollectionReference): NotesRepository = NotesRepositoryImpl(notesRef = notesRef)

}