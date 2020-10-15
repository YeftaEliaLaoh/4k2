package com.example.a4k.core.network.responses;

data class Location(

    val street: Street,

    val city: String,

    val state: String,

    val postcode: String
)