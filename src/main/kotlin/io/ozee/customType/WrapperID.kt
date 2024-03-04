package io.ozee.customType

import java.util.*

data class WrapperID(
    private val id: UUID,
    private var prefix: String
) : Comparable<WrapperID>, java.io.Serializable {
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
        const val PREFIX_PARAMETER = "prefix"
    }

    constructor(prefix: String) : this(UUID.randomUUID(), prefix)
}
