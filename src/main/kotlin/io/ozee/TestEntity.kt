package io.ozee

import io.micronaut.data.annotation.event.PostLoad
import jakarta.persistence.*
import org.hibernate.annotations.JavaType
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
class TestEntity {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @JavaType(WrapperIDDataType::class)
    var id: WrapperID = WrapperID()

    @Column
    var name: String = ""

    @PostLoad
    fun onPostLoad() {
        id.replacePrefix(prefix)
    }


    val prefix: String = "DD-"
}

