// package example.micronaut.data.repository
//
// import example.micronaut.data.entity.Entity1
// import io.micronaut.data.repository.GenericRepository
// import io.micronaut.transaction.annotation.ReadOnly
// import jakarta.inject.Singleton
// import java.util.UUID
// import javax.persistence.EntityManager
// import javax.transaction.Transactional
// import javax.validation.constraints.NotBlank
//
// @Singleton
// class Entity1JpaRepository(
//     private val entityManager: EntityManager
// ) : GenericRepository<Entity1, UUID> {
//     @ReadOnly
//     fun findById(id: Long): Entity1? {
//         return entityManager.find(Entity1::class.java, id)
//     }
//
//     @Transactional
//     fun update(id: Long, name: @NotBlank String?): Int {
//         return entityManager.createQuery("UPDATE Genre g SET name = :name where id = :id")
//             .setParameter("name", name)
//             .setParameter("id", id)
//             .executeUpdate()
//     }
// }
