package maria.incyberspace.notesapp.presentation.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import maria.incyberspace.notesapp.domain.model.Response.Loading
import maria.incyberspace.notesapp.domain.model.Response.Success
import maria.incyberspace.notesapp.domain.repository.AddNoteResponse
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import maria.incyberspace.notesapp.domain.repository.DeleteNoteResponse
import maria.incyberspace.notesapp.domain.repository.EditNoteResponse
import maria.incyberspace.notesapp.domain.repository.NotesRepository
import maria.incyberspace.notesapp.domain.repository.NotesResponse
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val noteRepo: NotesRepository
): ViewModel() {

    var notesResponse by mutableStateOf<NotesResponse>(Loading)
        private set
    var addNoteResponse by mutableStateOf<AddNoteResponse>(Success(false))
        private set
    var editNoteResponse by mutableStateOf<EditNoteResponse>(Success(false))
        private set
    var deleteNoteResponse by mutableStateOf<DeleteNoteResponse>(Success(false))
        private set

    init {
        getNotes(authRepo.currentUser!!.uid)
    }

    private fun getNotes(userId: String) = viewModelScope.launch {
        noteRepo.getNotes(userId).collect {
            response -> notesResponse = response
        }
    }

    fun addNote(title: String, content: String, timestamp: Timestamp, userId: String) = viewModelScope.launch {
        addNoteResponse = Loading
        addNoteResponse = noteRepo.addNote(title, content, timestamp, userId)
    }


    fun deleteNote(noteId: String) = viewModelScope.launch {
        deleteNoteResponse = Loading
        deleteNoteResponse = noteRepo.deleteNote(noteId)
    }

    fun signOut() = authRepo.signOut()
}