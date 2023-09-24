package maria.incyberspace.notesapp.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import maria.incyberspace.notesapp.components.DeleteIcon
import maria.incyberspace.notesapp.domain.model.Note

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteCard(
    note: Note,
    deleteNote: () -> Unit,
    editNote: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth(),
        backgroundColor = Color.LightGray,
        contentColor = Color.White,
        onClick = editNote
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Column {
                /* todo: make those components
                *   Note title, Note content, Delete icon */
                NoteTitle(noteTitle = note.title.orEmpty())
                NoteContent(noteContent = note.content.orEmpty())
            }
            Spacer(modifier = Modifier.weight(1f))
            DeleteIcon(deleteNote)
        }
        
    }
}