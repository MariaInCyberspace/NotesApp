package maria.incyberspace.notesapp.core

import android.content.Context
import android.util.Log.*
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import maria.incyberspace.notesapp.core.Constants.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = e(TAG, e.stackTraceToString())

        fun showMessage(
            context: Context,
            message: String?
        ) = makeText(context, message, LENGTH_LONG).show()
    }
}