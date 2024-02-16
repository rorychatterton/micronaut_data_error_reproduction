package io.ozee

import io.micronaut.data.annotation.event.PostLoad
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
class TestEntity {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    var id: WrapperID = WrapperID()

    @PostLoad
    fun onPostLoad() {
        id.replacePrefix(prefix)
    }


    val prefix: String = "DD-"
}

