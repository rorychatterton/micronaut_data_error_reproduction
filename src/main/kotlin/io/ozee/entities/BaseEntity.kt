package io.ozee.entities

import io.ozee.customType.WrapperID

interface BaseEntity {

    var id: WrapperID

    companion object {
        const val PREFIX_PARAMETER = "prefix"
    }
}

