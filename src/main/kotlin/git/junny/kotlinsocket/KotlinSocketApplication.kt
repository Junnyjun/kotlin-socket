package git.junny.kotlinsocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSocketApplication

fun main(args: Array<String>) {
    runApplication<KotlinSocketApplication>(*args)
}
