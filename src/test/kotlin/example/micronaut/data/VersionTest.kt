package example.micronaut.data

import example.micronaut.data.entity.Entity1
import example.micronaut.data.entity.Entity2
import example.micronaut.data.repository.Entity1Repository
import example.micronaut.data.repository.Entity2Repository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.transaction.SynchronousTransactionManager
import io.micronaut.transaction.TransactionDefinition
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import java.sql.Connection
import java.util.UUID

@Testcontainers
@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VersionTest {

    @Inject
    lateinit var entity1Repository: Entity1Repository

    @Inject
    lateinit var entity2Repository: Entity2Repository

    @Inject
    private lateinit var transactionManager: SynchronousTransactionManager<Connection>

    @Test
    fun `entity2 should check version, when update entity1`() {
        val a = transactionManager.execute(TransactionDefinition.DEFAULT) {
            // var v = entity2Repository.save(
            //     Entity2(
            //         id = UUID.randomUUID()
            //     )
            // )
            entity1Repository.save(
                Entity1(
                    id = UUID.randomUUID(),
                    entity2 = Entity2(
                        id = UUID.randomUUID()
                    )
                    // entity2 = v!!
                )
            )
        }

        transactionManager.execute(TransactionDefinition.DEFAULT) {
            var b = entity1Repository.findById(a.id)
            println("update1 entity1.version is ${b!!.version}, entity2.version is ${b!!.entity2!!.version}")
            var bbb = entity1Repository.update(
                b!!.copy(
                    name = "zhuhui",
                    entity2 = b.entity2!!.copy(
                        name = "zaoan"
                    )
                )
            )
            println("update1 entity1.version is ${bbb!!.version}, entity2.version is ${bbb!!.entity2!!.version}")
        }

        transactionManager.execute(TransactionDefinition.DEFAULT) {
            val bbbb = entity1Repository.findById(a.id)
            println("final entity1.version is ${bbbb!!.version}, entity2.version is ${bbbb!!.entity2!!.version}, name is ${bbbb!!.name}")
        }
    }
}
