package maria.incyberspace.notesapp.presentation.signup.components


import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import maria.incyberspace.notesapp.core.Constants.Constants.SIGN_UP_SCREEN
import maria.incyberspace.notesapp.components.BackIcon

@Composable
fun SignUpTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = SIGN_UP_SCREEN
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}