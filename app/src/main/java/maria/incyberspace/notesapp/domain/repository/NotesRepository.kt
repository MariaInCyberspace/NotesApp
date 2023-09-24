package maria.incyberspace.notesapp.domain.repository

import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.Flow
import maria.incyberspace.notesapp.domain.model.Note
import maria.incyberspace.notesapp.domain.model.Response

typealias Notes = List<Note>
typealias NotesResponse = Response<Notes>
typealias AddNoteResponse = Response<Boolean>
typealias DeleteNoteResponse = Response<Boolean>
typealias EditNoteResponse = Response<Boolean>
typealias GetNoteResponse = Response<Note?>

interface NotesRepository {
    fun getNotes(userId: String): Flow<NotesResponse>

    suspend fun addNote(title: String?, content: String?, timestamp: Timestamp, userId: String): AddNoteResponse

    suspend fun deleteNote(noteId: String): DeleteNoteResponse

    // todo: write an editNote function
    suspend fun updateNote(noteId: String, title: String? = "", content: String? = "", timestamp: Timestamp): EditNoteResponse

    suspend fun getNote(noteId: String): GetNoteResponse

//    suspend fun getNote(noteId: String): Note?
}