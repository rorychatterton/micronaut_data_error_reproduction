package io.ozee.customType

import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType
import java.util.*

@TypeDef(type = DataType.UUID, converter = WrapperIDConverter::class)
data class WrapperID(
    private val id: UUID,
    private var prefix: String = PLACEHOLDER_PREFIX
): Comparable<WrapperID> {
    override fun compareTo(other: WrapperID): Int {
        return toUUID().compareTo(other.toUUID())
    }

    override fun toString(): String {
        // base64 encode id
        val encodedID = Base64.getEncoder().encodeToString(id.toString().toByteArray())
        return "${prefix}${encodedID}"
    }

    fun toUUID(): UUID {
        return id
    }

    fun replacePrefix(newPrefix: String) {
        prefix = newPrefix
    }

    companion object {
        const val PLACEHOLDER_PREFIX = "PREFIX-"
    }

    constructor(prefix: String): this(UUID.randomUUID(), prefix)
}
