package maria.incyberspace.notesapp.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import maria.incyberspace.notesapp.components.ProgressBar
import maria.incyberspace.notesapp.domain.model.Response.Loading
import maria.incyberspace.notesapp.domain.model.Response.Success
import maria.incyberspace.notesapp.domain.model.Response.Failure
import maria.incyberspace.notesapp.domain.repository.Notes
import maria.incyberspace.notesapp.presentation.notes.NotesViewModel

@Composable
fun Notes(
    viewModel: NotesViewModel = hiltViewModel(),
    notesContent: @Composable (notes: Notes) -> Unit
) {
    when (val notesResponse = viewModel.notesResponse) {
        is Loading -> ProgressBar()
        is Success -> notesContent(notesResponse.data)
        is Failure -> print(notesResponse.e)
    }

}