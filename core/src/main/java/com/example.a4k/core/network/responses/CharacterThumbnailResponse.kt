package com.example.a4k.core.network.responses

import com.example.a4k.core.annotations.OpenForTesting

/**
 * Marvel API character thumbnail network response.
 *
 * @param path The directory path of to the image.
 * @param extension The file extension for the image.
 */
@OpenForTesting
data class CharacterThumbnailResponse(
    val path: String,
    val extension: String
)
