package maria.incyberspace.notesapp.presentation.signup.components

import android.widget.ProgressBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import maria.incyberspace.notesapp.domain.model.Response
import maria.incyberspace.notesapp.presentation.signup.SignUpViewModel
import maria.incyberspace.notesapp.components.ProgressBar


@Composable
fun SignUp(
    viewModel: SignUpViewModel = hiltViewModel(),
//    sendEmailVerification: () -> Unit,
//    showVerifyEmailMessage: () -> Unit
) {
    when(val signUpResponse = viewModel.signUpResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val isUserSignedUp = signUpResponse.data
//            LaunchedEffect(isUserSignedUp) {
//                if (isUserSignedUp) {
//                    sendEmailVerification()
//                    showVerifyEmailMessage()
//                }
//            }
        }
        is Response.Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
            }
        }
    }
}