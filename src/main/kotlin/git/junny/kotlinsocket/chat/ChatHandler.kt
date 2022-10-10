package git.junny.kotlinsocket.chat

import git.junny.kotlinsocket.chat.repository.ChatSession
import git.junny.kotlinsocket.chat.repository.ChatSessionRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class ChatHandler(
    private val chatSessionRepository: ChatSessionRepository
) : TextWebSocketHandler() {
    private val sessions: List<WebSocketSession> = mutableListOf()
    private val logger = KotlinLogging.logger {}

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        var i: Int = 0
        while (true) {
            session.sendMessage(TextMessage("Hi " + i++ + "!"))
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "connect client :: ${session.id}" }
        chatSessionRepository.save(ChatSession("junny", session.id))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "disconnect client :: ${session.id}" }
        chatSessionRepository.deleteBySessionId(session.id)
    }
}