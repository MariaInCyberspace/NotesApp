package maria.incyberspace.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import maria.incyberspace.notesapp.core.Constants.Constants.NOTE_ID
import maria.incyberspace.notesapp.presentation.signin.SignInScreen
import maria.incyberspace.notesapp.presentation.signup.SignUpScreen
import maria.incyberspace.notesapp.presentation.notes.NotesScreen
import maria.incyberspace.notesapp.navigation.Screen.SignInScreen
import maria.incyberspace.notesapp.navigation.Screen.SignUpScreen
import maria.incyberspace.notesapp.navigation.Screen.NotesScreen
import maria.incyberspace.notesapp.presentation.note.NoteDetailsScreen


@Composable
@ExperimentalComposeUiApi
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SignInScreen.route
    ) {
        composable(
            route = SignInScreen.route
        ) {
            SignInScreen(
                navigateToSignUpScreen = {
                    navController.navigate(SignUpScreen.route)
                }
            )
        }
        composable(
            route = SignUpScreen.route
        ) {
            SignUpScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = NotesScreen.route
        ) {
            NotesScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                onNoteClick = {
                    noteId ->
                    navController.navigate(Screen.NewNoteScreen.route + "?id={$noteId}") {
                        launchSingleTop = true
                    }
                },
                navToAddNewNote = {
                    navController.navigate(Screen.NewNoteScreen.route)
                }
            )
        }


        composable(
            route = Screen.NewNoteScreen.route + "?id={$NOTE_ID}",
            arguments = listOf(
                navArgument(NOTE_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            // todo Selected note screen
            entry ->
            NoteDetailsScreen(noteId = entry.arguments?.getString(NOTE_ID) as String) {
                navController.navigateUp()
            }
        }
    }
}