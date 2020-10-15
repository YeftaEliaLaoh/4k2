package com.example.a4k.dynamicfeatures.characterslist.ui.detail.model

import com.example.a4k.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment

/**
 * Model view to display on the screen [CharacterDetailFragment].
 */
data class CharacterDetail(
    val id: Long,
    val username: String,
    val email: String,
    val imageUrl: String
)
