package io.ozee

import io.ozee.customType.WrapperID
import org.hibernate.HibernateException
import org.hibernate.MappingException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.hibernate.internal.util.config.ConfigurationHelper
import org.hibernate.service.ServiceRegistry
import org.hibernate.type.Type
import java.io.Serializable
import java.util.*

/**
 * This class allows us to use UUIDv7 as the primary key for our entities.
 * This means that IDs now have an implicit createdAt timestamp which enforces ordering. This simplifies
 * pagination as we no longer need to append the createdAT timestamp to have
 * cardinally sorted objects.
 *
 * This also brings it into alignment with industry use of API Primary keys. That is, typically a prefix + a UUID
 * with base32 encoding.
 */
class UUID7Generator : IdentifierGenerator {
    private var valuePrefix: String = WrapperID.PLACEHOLDER_PREFIX

    @Throws(MappingException::class)
    override fun configure(type: Type?, params: Properties?, serviceRegistry: ServiceRegistry?) {
        valuePrefix = ConfigurationHelper.getString(WrapperID.PREFIX_PARAMETER, params, WrapperID.PLACEHOLDER_PREFIX)
    }


    @Throws(HibernateException::class)
    override fun generate(session: SharedSessionContractImplementor, `object`: Any): Serializable {
        return WrapperID(valuePrefix)
    }
}
