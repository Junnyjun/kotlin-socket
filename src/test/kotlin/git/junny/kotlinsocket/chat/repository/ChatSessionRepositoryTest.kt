package git.junny.kotlinsocket.chat.repository

import git.junny.kotlinsocket.RedisConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull

@DataRedisTest
@Import(RedisConfig::class)
internal class ChatSessionRepositoryTest{

    @Autowired
    private lateinit var chatSessionRepository: ChatSessionRepository

    @Test
    fun `save test`(){
        val chatSession = ChatSession("junny", "1234")
        val result = chatSessionRepository.save(chatSession)
        assertEquals(chatSession, result)
    }
    @Test
    fun `find test`(){
        val chatSession = ChatSession("junny", "1234")
        chatSessionRepository.save(chatSession)
        val result = chatSessionRepository.findByIdOrNull(1L) ?: fail("not found")
        assertEquals(chatSession, result)
    }
    @Test
    fun `delete test`(){
        val chatSession = ChatSession( "junny", "1234")
        chatSessionRepository.save(chatSession)
        chatSessionRepository.deleteById(1L)
        val result = chatSessionRepository.findByIdOrNull(1L)
        assertNull(result)
    }
    @Test
    fun `update test`(){
        val chatSession = ChatSession("junny", "1234")
        chatSessionRepository.save(chatSession)
        val result = chatSessionRepository.findByIdOrNull(1L) ?: fail("not found")
        assertEquals(chatSession, result)
        val updateChatSession = ChatSession( "new", "1234")
        chatSessionRepository.save(updateChatSession)
        val updateResult = chatSessionRepository.findByIdOrNull(1L) ?: fail("not found")
        assertEquals(updateChatSession, updateResult)
    }
    @Test
    fun `deleteAll test`(){
        val chatSession = ChatSession( "junny", "1234")
        val id = chatSessionRepository.save(chatSession).id
        chatSessionRepository.deleteAll()
        val result = chatSessionRepository.findByIdOrNull(1L)
        assertNull(result)
    }
    @Test
    fun `count test`(){
        val chatSession = ChatSession("junny", "1234")
      chatSessionRepository.save(chatSession).id
        val result = chatSessionRepository.count()
        assertEquals(1, result)
    }
    @Test
    fun `existsById test`(){
        val chatSession = ChatSession("junny", "1234")
        chatSessionRepository.save(chatSession)
        val result = chatSessionRepository.existsById(1L)
        assertTrue(result)
    }
    @Test
    fun `findAll test`(){
        val chatSession = ChatSession("junny", "1234")
        chatSessionRepository.save(chatSession)
        val result = chatSessionRepository.findAll()
        assertEquals(1, result.count())
    }

}