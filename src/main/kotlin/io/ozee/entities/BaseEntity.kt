package io.ozee.entities

import io.micronaut.data.annotation.event.PostLoad
import io.ozee.customType.WrapperID
import io.ozee.customType.WrapperIDDataType
import jakarta.persistence.*
import org.hibernate.annotations.JavaType
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
abstract class BaseEntity(private val prefix: String) {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @JavaType(WrapperIDDataType::class)
    var id: WrapperID = WrapperID(prefix)

    @Column
    var name: String = ""

    @PostLoad
    fun onPostLoad() {
        id.replacePrefix(prefix)
    }

}

