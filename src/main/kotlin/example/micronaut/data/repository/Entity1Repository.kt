package example.micronaut.data.repository

import example.micronaut.data.entity.Entity1
import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Repository
import java.util.UUID
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.REQUIRES_NEW)
@Join(value = "entity2", type = Join.Type.LEFT_FETCH)
interface Entity1Repository : KotlinUpdateRepository<Entity1, UUID> {
    fun findById(id: UUID): Entity1
}
