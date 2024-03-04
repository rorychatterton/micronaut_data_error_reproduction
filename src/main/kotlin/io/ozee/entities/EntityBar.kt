package io.ozee.entities

import io.ozee.customType.WrapperID
import io.ozee.customType.WrapperIDType
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.annotations.Type


@Entity
class EntityBar(

    @Column
    var name: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foo")
    val foo: EntityFoo? = null,

    @ManyToMany
    @JoinTable(
        name = "foo_rels",
        joinColumns = [JoinColumn(name = "foo_id")],
        inverseJoinColumns = [JoinColumn(name = "bar_id")]
    )
    var manyFoos: MutableCollection<EntityFoo> = mutableListOf()

) : BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "ozeeIDGenerator")
    @GenericGenerator(
        name = "ozeeIDGenerator",
        strategy = "io.ozee.UUID7Generator",
        parameters = [
            Parameter(name = WrapperID.PREFIX_PARAMETER, value = PREFIX)
        ]
    )
    @Type(
        WrapperIDType::class,
        parameters = [Parameter(name = BaseEntity.PREFIX_PARAMETER, value = PREFIX)]
    )
    override lateinit var id: WrapperID

    companion object {
        const val PREFIX: String = "DD-"
    }
}

