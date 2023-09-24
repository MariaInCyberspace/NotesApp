package maria.incyberspace.notesapp.presentation.notes.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NoteTitle(
    noteTitle: String
) {
    Text(
        text = noteTitle,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )

}