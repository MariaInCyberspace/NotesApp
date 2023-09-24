package maria.incyberspace.notesapp.navigation

import maria.incyberspace.notesapp.core.Constants.Constants.NEW_NOTE_SCREEN
import maria.incyberspace.notesapp.core.Constants.Constants.NOTES_SCREEN
import maria.incyberspace.notesapp.core.Constants.Constants.SELECTED_NOTE_SCREEN
import maria.incyberspace.notesapp.core.Constants.Constants.SIGN_IN_SCREEN
import maria.incyberspace.notesapp.core.Constants.Constants.SIGN_UP_SCREEN

sealed class Screen(val route: String) {
    object SignInScreen: Screen(SIGN_IN_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object NotesScreen: Screen(NOTES_SCREEN)
    object SelectedNoteScreen: Screen(SELECTED_NOTE_SCREEN)
    object NewNoteScreen: Screen(NEW_NOTE_SCREEN)
}