package maria.incyberspace.notesapp.domain.model

data class NoteDetailsUIState(
    val title: String? = "",
    val content: String? = "",
    val noteAddedStatus: Boolean = false,
    val updateNoteStatus: Boolean = false,
    val selectedNote: Note? = null
)

