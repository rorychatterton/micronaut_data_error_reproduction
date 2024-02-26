package io.ozee.customType

import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.AbstractClassJavaType
import org.hibernate.type.descriptor.java.UUIDJavaType
import java.util.*

class WrapperIDDataType: AbstractClassJavaType<WrapperID>(WrapperID::class.java) {
    override fun <X : Any?> unwrap(value: WrapperID?, clazz: Class<X>?, options: WrapperOptions?): X {
        return UUIDJavaType.INSTANCE.unwrap(value?.toUUID(), clazz, options)
    }

    override fun <X : Any?> wrap(value: X?, options: WrapperOptions?): WrapperID? {
        when(value) {
            is UUID -> return WrapperID(value)
            is WrapperID -> return value
        }

        return value
            ?.let { UUIDJavaType.INSTANCE.wrap(value, options) }
            ?.let { WrapperID(it, "PREFIX") }
    }
}
