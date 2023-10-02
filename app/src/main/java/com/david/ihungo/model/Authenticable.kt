package com.david.ihungo.model

import kotlinx.serialization.*

@Serializable
data class Authenticable (
    val ok: Boolean?,
    val token: String?,
)
