package com.example.kbbi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class KBBIEntry(
    val nama: String = "",
    val bentuk_tidak_baku: List<String> = emptyList()
)

@Serializable
data class KBBIData(
    val entri: List<KBBIEntry>
)

@Serializable
data class KBBIResponse(
    val data: KBBIData
)
