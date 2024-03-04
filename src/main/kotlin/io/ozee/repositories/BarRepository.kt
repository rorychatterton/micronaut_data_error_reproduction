package io.ozee.repositories

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import io.ozee.customType.WrapperID
import io.ozee.entities.EntityBar

@Repository
interface BarRepository : CrudRepository<EntityBar, WrapperID> {
    fun getById(id: WrapperID): EntityBar?
}
