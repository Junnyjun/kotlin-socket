package git.junny.kotlinsocket.chat.repository

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@RedisHash(value = "people", timeToLive = 30)
class ChatSession(
    @Id
    val id: String,
    val username: String,
    val sessionId: String,
    val regDate: LocalDateTime = now()
    ){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChatSession

        if (id != other.id) return false
        if (username != other.username) return false
        if (sessionId != other.sessionId) return false
        if (regDate != other.regDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + sessionId.hashCode()
        result = 31 * result + regDate.hashCode()
        return result
    }
}