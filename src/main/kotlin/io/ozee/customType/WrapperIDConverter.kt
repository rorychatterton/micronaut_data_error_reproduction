package io.ozee.customType

import io.micronaut.core.convert.ConversionContext
import io.micronaut.data.model.runtime.convert.AttributeConverter
import jakarta.inject.Singleton
import java.util.*

@Singleton
open class WrapperIDConverter : AttributeConverter<WrapperID, UUID> {
    override fun convertToPersistedValue(entityValue: WrapperID?, context: ConversionContext?): UUID? {
        return entityValue?.toUUID()
    }

    override fun convertToEntityValue(persistedValue: UUID?, context: ConversionContext?): WrapperID? {
        return persistedValue?.let { WrapperID(it) }
    }
}
