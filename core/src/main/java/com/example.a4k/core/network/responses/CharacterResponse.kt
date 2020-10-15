package com.example.a4k.core.network.responses

import com.example.a4k.core.annotations.OpenForTesting

/**
 * API character network response item.
 *
 * @param id The unique ID of the character resource.
 * @param name The name of the character.
 * @param description A short bio or description of the character.
 * @param thumbnail The representative image for this character.
 */
@OpenForTesting
data class CharacterResponse(

    val gender: String,

    val name: Name,

    val location: Location,

    val email: String,

    val login: Login,

    val registered: Registered,

    val dob: Dob,

    val phone: String,

    val cell: String,

    val id: Id,

    val picture: Picture,

    val nat: String
)
