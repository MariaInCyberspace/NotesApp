package maria.incyberspace.notesapp.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import maria.incyberspace.notesapp.core.Constants.Constants.CONTENT
import maria.incyberspace.notesapp.core.Constants.Constants.TIMESTAMP
import maria.incyberspace.notesapp.core.Constants.Constants.TITLE
import maria.incyberspace.notesapp.core.Constants.Constants.USER_ID
import maria.incyberspace.notesapp.domain.model.Note
import maria.incyberspace.notesapp.domain.repository.AddNoteResponse
import maria.incyberspace.notesapp.domain.repository.DeleteNoteResponse
import maria.incyberspace.notesapp.domain.repository.NotesRepository
import maria.incyberspace.notesapp.domain.model.Response.Success
import maria.incyberspace.notesapp.domain.model.Response.Failure
import maria.incyberspace.notesapp.domain.repository.EditNoteResponse
import maria.incyberspace.notesapp.domain.repository.NotesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(private val notesRef: CollectionReference): NotesRepository {

    override fun getNotes(userId: String): Flow<NotesResponse> {
        return callbackFlow {
            val snapshotListener = notesRef.orderBy(TIMESTAMP).whereEqualTo(USER_ID, userId).addSnapshotListener { snapshot, ex ->
                val notesResponse = if (snapshot != null) {
                    val notes = snapshot.toObjects(Note::class.java)
                    Success(notes)
                } else {
                    Failure(ex)
                }
                trySend(notesResponse)
            }
            awaitClose { snapshotListener.remove() }
        }
    }

    override suspend fun addNote(
        title: String?,
        content: String?,
        timestamp: Timestamp,
        userId: String
    ): AddNoteResponse {
        return try {
            // autogenerate id for the new entry
            val noteId = notesRef.document().id
            // Create new note to add
            val note = Note(id = noteId, title = title, content = content, timestamp = timestamp, userId = userId)
            notesRef.document(noteId).set(note).await() // Add note
            Success(true)
        } catch (ex: Exception) {
            Failure(ex)
        }
    }

    override suspend fun deleteNote(noteId: String): DeleteNoteResponse {
        return try {
            notesRef.document(noteId).delete().await()
            Success(true)
        } catch (ex: Exception) {
            Failure(ex)
        }
    }

    override suspend fun updateNote(
        noteId: String,
        title: String?,
        content: String?,
        timestamp: Timestamp
    ): EditNoteResponse {
        return try {
            if (title != "") {
                notesRef.document(noteId).update(TITLE, title).await()
            }
            if (content != "") {
                notesRef.document(noteId).update(CONTENT, content).await()
            }
            notesRef.document(noteId).update(TIMESTAMP, timestamp).await()
            Success(true)
        } catch (ex: Exception) {
            Failure(ex)
        }
    }

    override suspend fun getNote(noteId: String) = try {
        val note = notesRef.document(noteId).get().await().toObject(Note::class.java)
        Success(note)
    } catch (ex: Exception) {
        Failure(ex)
    }
}