package com.panasetskaia.countrieswithcompose.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamesContainerDto(

    @SerialName("common")
    val commonName: String? = null,

    @SerialName("official")
    val officialName: String? = null
)
