package example.micronaut.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.Version

@Entity
@Table(name = "entity1")
data class Entity1(
    @Id
    val id: UUID,
    @Column(name = "name")
    val name: String? = null,
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    var createdAt: Instant? = null,
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    var updatedAt: Instant? = null,
    @Version
    val version: Long? = 0,

    @OneToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    var entity2: Entity2? = null
)
