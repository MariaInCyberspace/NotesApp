package maria.incyberspace.notesapp.presentation.signin

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import maria.incyberspace.notesapp.core.Utils.Companion.showMessage
import maria.incyberspace.notesapp.presentation.signin.components.SignIn
import maria.incyberspace.notesapp.presentation.signin.components.SignInContent
import maria.incyberspace.notesapp.presentation.signin.components.SignInTopBar

@Composable
@ExperimentalComposeUiApi
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            SignInTopBar()
        },
        content = { padding ->
            SignInContent(
                padding = padding,
                signIn = { email, password ->
                    viewModel.signIn(email, password)
                },
                navigateToSignUpScreen = navigateToSignUpScreen
            )
        }
    )

    SignIn(showErrorMessage = {errorMessage ->  showMessage(context, errorMessage)})
}