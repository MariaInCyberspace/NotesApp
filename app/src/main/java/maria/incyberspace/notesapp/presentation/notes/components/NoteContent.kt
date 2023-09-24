package maria.incyberspace.notesapp.presentation.notes.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import maria.incyberspace.notesapp.domain.model.Note

@Composable
fun NoteContent(
    noteContent: String
) {
    Text(
        text = noteContent,
        fontSize = 12.sp,
        overflow = TextOverflow.Clip
    )
}