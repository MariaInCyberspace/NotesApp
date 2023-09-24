package maria.incyberspace.notesapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val colorPallette = lightColors(
    primary = LightBlue,
    primaryVariant = DarkBlue,
    secondary = Pink
)

@Composable
fun NotesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colorPallette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}