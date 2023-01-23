package example.micronaut.data.entity


import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "entity2")
data class Entity2(
    @javax.persistence.Id
    val id: UUID,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    var createdAt: Instant? = null,
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    var updatedAt: Instant? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val entity1: Entity1? = null,

    @javax.persistence.Version
    val version: Long? = 0
)
