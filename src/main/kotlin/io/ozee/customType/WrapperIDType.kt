package io.ozee.customType

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.SqlTypes
import org.hibernate.usertype.DynamicParameterizedType
import org.hibernate.usertype.EnhancedUserType
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

class WrapperIDType : UserType<WrapperID>, DynamicParameterizedType, EnhancedUserType<WrapperID> {

    private var prefix: String = "ID"

    override fun setParameterValues(p0: Properties?) {
        prefix = p0?.getProperty("prefix") ?: "ID"
    }

    override fun equals(p0: WrapperID?, p1: WrapperID?): Boolean {
        return p0 == p1
    }

    override fun hashCode(p0: WrapperID?): Int {
        return p0.hashCode()
    }

    override fun toString(p0: WrapperID?): String = p0.toString()

    override fun getSqlType(): Int = SqlTypes.UUID

    override fun returnedClass(): Class<WrapperID> = WrapperID::class.java

    override fun isMutable(): Boolean = false

    override fun assemble(p0: Serializable?, p1: Any?): WrapperID {
        return p0 as WrapperID
    }

    override fun fromStringValue(p0: CharSequence?): WrapperID = WrapperID(UUID.fromString(p0.toString()), prefix)

    override fun toSqlLiteral(p0: WrapperID?): String = p0?.toUUID().toString()

    override fun disassemble(p0: WrapperID?): Serializable {
        return p0 as Serializable
    }

    override fun deepCopy(p0: WrapperID?): WrapperID? {
        return p0?.let { WrapperID(it.toString()) }
    }

    override fun nullSafeSet(p0: PreparedStatement, p1: WrapperID, p2: Int, p3: SharedSessionContractImplementor?) {
        p0.setString(p2, p1.toUUID().toString())
    }

    override fun nullSafeGet(p0: ResultSet?, p1: Int, p2: SharedSessionContractImplementor?, p3: Any?): WrapperID? {
        return p0?.getString(p1)?.let { WrapperID(UUID.fromString(it), prefix) }
    }

}
