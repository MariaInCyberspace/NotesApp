package maria.incyberspace.notesapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import maria.incyberspace.notesapp.core.Constants.Constants.DELETE_NOTE

@Composable
fun DeleteIcon(
    deleteNote: () -> Unit
) {
    IconButton(onClick = deleteNote) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = DELETE_NOTE
        )
    }

}