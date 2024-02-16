package io.ozee

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface TestEntityRepository: CrudRepository<TestEntity, WrapperID> {
    fun getById(id: WrapperID): TestEntity
}
