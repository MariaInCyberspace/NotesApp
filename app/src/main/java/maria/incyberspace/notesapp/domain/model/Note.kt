package maria.incyberspace.notesapp.domain.model

import com.google.firebase.Timestamp

data class Note(var id: String? = null,
                var title: String? = null,
                var content: String? = null,
                var timestamp: Timestamp? = null,
                var userId: String? = null)