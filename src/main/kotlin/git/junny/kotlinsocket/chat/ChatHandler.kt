package git.junny.kotlinsocket.chat

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@Component
class ChatHandler() : TextWebSocketHandler() {
    private val sessions: List<WebSocketSession> = mutableListOf()
    private val logger = KotlinLogging.logger {}

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        for (s in sessions) {
            s.sendMessage(TextMessage("Hi " + payload.get(0) + "!"))
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        logger.info { "connect client :: ${session.id}" }
        sessions.plus(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        logger.info { "disconnect client :: ${session.id}" }
        sessions.minus(session)
    }
}