package io.ozee.entities

import jakarta.persistence.*


@Entity
class EntityBar(
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

): BaseEntity(PREFIX) {
    companion object {
        const val PREFIX: String = "DD-"
    }
}

