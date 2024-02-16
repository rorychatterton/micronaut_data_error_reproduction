package io.ozee
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

@MicronautTest
class MicronautDataErrorTest(
    private val application: EmbeddedApplication<*>,
    private val repository: TestEntityRepository
): BehaviorSpec({
    Given("An entity exists in the database"){
        val entity = TestEntity()
        val savedEntity = repository.save(entity)
        When("I try to retrieve it") {
            val retrievedEntity = repository.getById(savedEntity.id)
            Then("I should get the entity") {
                retrievedEntity shouldNotBe null
                retrievedEntity!!
                entity.id shouldBe retrievedEntity.id
                println(entity.id)
                println(retrievedEntity.id)
            }
        }
        When("I Update it") {
            savedEntity.name = "Updated Name"
            val updatedEntity = repository.update(savedEntity)
            Then("I should get the updated entity") {
                updatedEntity.name shouldBe "Updated Name"
            }
        }
        When("I delete it") {
            repository.delete(savedEntity)
            Then("I should not be able to retrieve it") {
                val retrievedEntity = repository.getById(savedEntity.id)
                retrievedEntity shouldBe null
            }
        }
    }

    Given("Many entities exist in the database") {
        val entities = (1..10).map { TestEntity() }
        val savedEntities = repository.saveAll(entities)
        When("I try to retrieve them") {
            val retrievedEntities = repository.findAll()
            Then("I should get all the entities") {
                retrievedEntities.size shouldBeGreaterThan 9
            }
        }
    }
})
