package maria.incyberspace.notesapp.presentation.notes.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import maria.incyberspace.notesapp.domain.repository.Notes

@Composable
fun NotesContent(
    padding: PaddingValues,
    notes: Notes,
    deleteNote: (noteId: String) -> Unit,
    editNote: (noteId: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
        items(items = notes) {
            note -> NoteCard(note = note,
                            deleteNote = {
                                note.id?.let { noteId -> deleteNote(noteId) }
                            },
                            editNote = {
                                note.id?.let { noteId -> editNote(noteId) }
                            }
            )
        }
    }
}
