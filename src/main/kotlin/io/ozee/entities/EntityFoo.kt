package io.ozee.entities

import jakarta.persistence.*


@Entity
class EntityFoo: BaseEntity(PREFIX) {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foo", cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH])
    val bars: MutableList<EntityBar> = mutableListOf()

    @ManyToMany(mappedBy = "manyFoos")
    val manyBars: MutableList<EntityBar> = mutableListOf()

    companion object {
        const val PREFIX: String = "DD-"
    }
}

