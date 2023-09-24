package maria.incyberspace.notesapp.presentation.notes

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import maria.incyberspace.notesapp.core.Constants.Constants.NOTES_SCREEN
import maria.incyberspace.notesapp.components.TopBar
import maria.incyberspace.notesapp.presentation.notes.components.AddNote
import maria.incyberspace.notesapp.presentation.notes.components.Notes
import maria.incyberspace.notesapp.presentation.notes.components.NotesContent

@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    onNoteClick: (noteId: String) -> Unit,
    navToAddNewNote: () -> Unit,
    navigateBack: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                title = NOTES_SCREEN,
                signOut = {
                    viewModel.signOut()
                }
            )
        },
        content = { padding ->
            Notes(
                notesContent = {
                    notes ->
                    NotesContent(
                        padding = padding,
                        notes = notes,
                        deleteNote = {
                            noteId -> viewModel.deleteNote(noteId)
                        },
                        editNote = {
                            // todo finish writing noteId ->
                            noteId -> onNoteClick.invoke(noteId)
                        }
                    )
                }
            )
        },
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AddNote(onClick = navToAddNewNote)
        }
    )

}