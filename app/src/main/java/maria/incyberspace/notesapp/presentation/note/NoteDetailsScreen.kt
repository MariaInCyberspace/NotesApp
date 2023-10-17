package maria.incyberspace.notesapp.presentation.note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import maria.incyberspace.notesapp.core.Constants
import maria.incyberspace.notesapp.core.Utils
import maria.incyberspace.notesapp.ui.theme.NotesTheme

@Composable
fun NoteDetailsScreen(
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    noteId: String,
    navigateBack: () -> Unit
) {
    val noteState = viewModel.noteState
//    val isNoteIdNotBlank = noteId.isNotBlank() // todo find a better solution...
    val isNoteIdBlank = noteId == Constants.Constants.NOTE_ID
    val isFormNotBlank = noteState.title!!.isNotBlank() && noteState.content!!.isNotBlank()
    val icon = if (!isNoteIdBlank) {
        Icons.Default.Refresh
    } else {
        Icons.Default.Check
    }
    val context = LocalContext.current
    // todo : write icon?
    LaunchedEffect(key1 = Unit) {
        if (!isNoteIdBlank) {
            viewModel.getNote(noteId)
        } else {
            viewModel.resetState()
        }
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AnimatedVisibility(visible = isFormNotBlank) {
                FloatingActionButton(onClick = {
                    if (!isNoteIdBlank) {
                        viewModel.updateNote(noteId)
                    } else {
                        viewModel.addNote()
                    }
                }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
    ) {
        padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (noteState.noteAddedStatus) {
                LaunchedEffect(key1 = Unit) {
                    scope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar("Note has been added successfully")
                        viewModel.resetNoteAddedStatus()
                        navigateBack.invoke()
                    }
                }
            }

            if (noteState.updateNoteStatus) {
                LaunchedEffect(key1 = Unit) {
                    scope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar("Note has been updated successfully")
                        viewModel.resetNoteAddedStatus()
                        navigateBack.invoke()
                    }
                }
            }
            
            OutlinedTextField(
                value = noteState.title,
                onValueChange = {
                    viewModel.onTitleChange(it)
                },
                label = {
                    Text(text = "Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = noteState.content!!,
                onValueChange = {
                    viewModel.onContentChange(it)
                },
                label = {
                    Text(text = "Notes")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)
            )
        }

    }

}

//@Preview
//@Composable
//fun PrevDetailNoteScreen() {
//    NotesTheme {
//        NoteDetailsScreen(noteId = "", viewModel = hiltViewModel()) {
//
//        }
//    }
//}