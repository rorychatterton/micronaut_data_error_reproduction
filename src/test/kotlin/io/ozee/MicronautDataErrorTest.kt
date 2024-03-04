package io.ozee
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.data.model.Pageable
import io.ozee.entities.EntityBar
import io.ozee.entities.EntityFoo
import io.ozee.repositories.BarRepository
import io.ozee.repositories.FooRepository

@MicronautTest
class MicronautDataErrorTest(
    private val application: EmbeddedApplication<*>,
    private val fooRepository: FooRepository,
    private val barRepository: BarRepository
): BehaviorSpec({
    Given("An entity exists in the database"){
        val entity = EntityFoo()
        val savedEntity = fooRepository.save(entity)
        When("I try to retrieve it") {
            val retrievedEntity = fooRepository.getById(savedEntity.id)
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
            val updatedEntity = fooRepository.update(savedEntity)
            Then("I should get the updated entity") {
                updatedEntity.name shouldBe "Updated Name"
            }
        }
        When("I delete it") {
            fooRepository.delete(savedEntity)
            Then("I should not be able to retrieve it") {
                val retrievedEntity = fooRepository.getById(savedEntity.id)
                retrievedEntity shouldBe null
            }
        }
    }

    Given("Many entities exist in the database") {
        val entities = (1..10).map { EntityFoo() }
        val savedEntities = fooRepository.saveAll(entities)
        When("I try to retrieve them") {
            val retrievedEntities = fooRepository.findAll()
            Then("I should get all the entities") {
                retrievedEntities.size shouldBeGreaterThan 9
            }
        }
        When("I try retrieve them in a pageable") {
            val retrievedEntities = fooRepository.findAllByIdAfter(savedEntities[0].id, Pageable.from(0, 5))
            Then("I should get the entities") {
                retrievedEntities.size shouldBeGreaterThan 0
            }
        }
    }


    Given("When a foobar is made with relationships") {
        val foo = EntityFoo()
        val bar = EntityBar(foo=foo, manyFoos = mutableListOf(foo))
        val savedFoo = fooRepository.save(foo)
        val savedBar = barRepository.save(bar)
        When("I create a relationship") {
            savedFoo.bars.add(savedBar)
            val updatedFoo = fooRepository.update(savedFoo)
            Then("I should be able to retrieve the relationship") {
                val retrievedFoo = fooRepository.getById(savedFoo.id)
            }
        }
        When("I query those relationships via foo") {
            val retrievedFoo = fooRepository.getById(savedFoo.id)
            Then("I should get the relationships") {
                retrievedFoo shouldNotBe null
                retrievedFoo!!.bars.size shouldBeGreaterThan 0
            }
        }
        When("I query those relationships via bar") {
            val retrievedBar = barRepository.getById(savedBar.id)
            Then("I should get the relationships") {
                println(retrievedBar?.id)
                retrievedBar shouldNotBe null
                retrievedBar!!.foo shouldNotBe null
                retrievedBar.manyFoos.size shouldBeGreaterThan 0
            }
        }
    }
})
