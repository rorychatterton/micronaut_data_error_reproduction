package io.ozee.entities

import io.ozee.customType.WrapperID
import io.ozee.customType.WrapperIDType
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.annotations.Type


@Entity
class EntityFoo : BaseEntity {

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

    @Column
    var name: String = ""

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "foo",
        cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH]
    )
    val bars: MutableList<EntityBar> = mutableListOf()

    @ManyToMany(mappedBy = "manyFoos")
    val manyBars: MutableList<EntityBar> = mutableListOf()

    companion object {
        const val PREFIX: String = "DD-"
    }
}

