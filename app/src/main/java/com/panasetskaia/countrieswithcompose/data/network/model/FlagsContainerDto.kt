package com.panasetskaia.countrieswithcompose.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlagsContainerDto(
    @SerialName("png")
    val flagPngUrl: String? = null
)
