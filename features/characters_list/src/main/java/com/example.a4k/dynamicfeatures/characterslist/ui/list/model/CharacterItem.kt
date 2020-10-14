package com.example.a4k.dynamicfeatures.characterslist.ui.list.model

import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListFragment

/**
 * Model view to display on the screen [CharactersListFragment].
 */
data class CharacterItem(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
