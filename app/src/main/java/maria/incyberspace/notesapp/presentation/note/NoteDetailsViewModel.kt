package maria.incyberspace.notesapp.presentation.note

import android.widget.ProgressBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import maria.incyberspace.notesapp.domain.model.Note
import maria.incyberspace.notesapp.domain.model.NoteDetailsUIState
import maria.incyberspace.notesapp.domain.model.Response.Success
import maria.incyberspace.notesapp.domain.model.Response.Loading
import maria.incyberspace.notesapp.domain.repository.AddNoteResponse
import maria.incyberspace.notesapp.domain.repository.AuthRepository
import maria.incyberspace.notesapp.domain.repository.GetNoteResponse
import maria.incyberspace.notesapp.domain.repository.NotesRepository
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val noteRepo: NotesRepository,
    authRepo: AuthRepository
): ViewModel() {

    var noteState by mutableStateOf(NoteDetailsUIState())
        private set

    var addNoteResponse by mutableStateOf<AddNoteResponse>(Success(false))
        private set

    var getNoteResponse by mutableStateOf<GetNoteResponse>(Loading)
        private set

    private val currentUserId = authRepo.currentUser!!.uid


    fun onTitleChange(title: String) {
        noteState = noteState.copy(title = title)
    }

    fun onContentChange(content: String) {
        noteState = noteState.copy(content = content)
    }

    fun addNote() = viewModelScope.launch {
        addNoteResponse = Loading
        addNoteResponse = noteRepo.addNote(title = noteState.title,
                                            content = noteState.content,
                                            timestamp = Timestamp.now(),
                                            userId = currentUserId)
        noteState = noteState.copy(noteAddedStatus = true)
    }


    fun setEditFields(note: Note) {
        noteState = noteState.copy(
            title = note.title,
            content = note.content
        )
    }

    fun getNote(noteId: String) = viewModelScope.launch {
        getNoteResponse = Loading
        getNoteResponse = noteRepo.getNote(noteId)
        (getNoteResponse as Success<Note?>).data?.let {
                note -> noteState = noteState.copy(selectedNote = note)
            noteState.selectedNote?.let { setEditFields(it) }
        }
    }


    fun updateNote(noteId: String) = viewModelScope.launch {
        noteRepo.updateNote(
            title = noteState.title,
            content = noteState.content,
            noteId = noteId,
            timestamp = Timestamp.now()
        )
            noteState = noteState.copy(updateNoteStatus = true)

    }

    fun resetNoteAddedStatus() {
        noteState = noteState.copy(
            noteAddedStatus = false,
            updateNoteStatus = false,
        )
    }

    fun resetState(){
        noteState = NoteDetailsUIState()
    }

}