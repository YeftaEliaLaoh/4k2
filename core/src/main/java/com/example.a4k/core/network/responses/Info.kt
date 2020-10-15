package com.example.a4k.core.network.responses;

import com.example.a4k.core.annotations.OpenForTesting;

@OpenForTesting
data class Info(

    val seed: String,

    val results: Int,

    val page: Int,

    val version: String

)
