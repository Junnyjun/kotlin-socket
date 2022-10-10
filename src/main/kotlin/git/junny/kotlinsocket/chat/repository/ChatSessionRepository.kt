package git.junny.kotlinsocket.chat.repository

import org.springframework.data.repository.CrudRepository

interface ChatSessionRepository : CrudRepository<ChatSession, Long> {
    fun deleteBySessionId(sessionId: String)
}