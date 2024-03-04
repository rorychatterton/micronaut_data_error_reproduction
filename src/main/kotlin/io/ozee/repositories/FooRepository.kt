package io.ozee.repositories

import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository
import io.ozee.customType.WrapperID
import io.ozee.entities.EntityFoo

@Repository
interface FooRepository : CrudRepository<EntityFoo, WrapperID> {
    fun getById(id: WrapperID): EntityFoo?

    fun findAllByIdAfter(
        id: WrapperID?,
        pageable: Pageable
    ): Page<EntityFoo>
}
