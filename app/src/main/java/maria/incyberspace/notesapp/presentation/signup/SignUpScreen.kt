package maria.incyberspace.notesapp.presentation.signup

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import maria.incyberspace.notesapp.presentation.signup.components.SignUp
import maria.incyberspace.notesapp.presentation.signup.components.SignUpContent
import maria.incyberspace.notesapp.presentation.signup.components.SignUpTopBar

@Composable
@ExperimentalComposeUiApi
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            SignUpTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            SignUpContent(
                padding = padding,
                signUp = { email, password ->
                    viewModel.signUp(email, password)
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp()
}