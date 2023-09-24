package maria.incyberspace.notesapp.core

class Constants {

    object Constants {
        // App
        const val TAG = "AppTag"

        // Buttons
        const val SIGN_IN_BUTTON = "Sign in"
        const val SIGN_UP_BUTTON = "Sign up"

        // Menu Items
        const val SIGN_OUT_ITEM = "Sign out"

        // Screens
        const val SIGN_IN_SCREEN = "Sign in"
        const val SIGN_UP_SCREEN = "Sign up"
        const val NOTES_SCREEN = "notes"
        const val NOTE_ID = "noteId"
        const val SELECTED_NOTE_SCREEN = "${NOTES_SCREEN}/{$NOTE_ID}"
        const val NEW_NOTE_SCREEN = "newNote"

        // Labels
        const val EMAIL_LABEL = "Email"
        const val PASSWORD_LABEL = "Password"

        // Useful
        const val EMPTY_STRING = ""
        const val VERTICAL_DIVIDER = "|"

        // Texts
        const val NO_ACCOUNT = "Don't have an account? Sign up"
        const val HAS_ACCOUNT = "Already have an account? Sign in"
        const val WELCOME_MESSAGE = "Welcome to our app"

        // Firestore
        const val NOTES = "notes"
        const val TITLE = "title"
        const val TIMESTAMP = "timestamp"
        const val CONTENT = "content"
        const val USER_ID = "userId"

        // Actions
        const val ADD_NOTE = "New note"
        const val DELETE_NOTE = "Delete this note"

        // Buttons
        const val ADD = "Add"
        const val DISMISS = "Dismiss"

        // Placeholders
        const val NOTE_TITLE = "Type the title of your note here.."
        const val NOTE_CONTENT = "Type what you would like to take note of.."
    }
}