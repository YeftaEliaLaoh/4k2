package com.example.a4k.dynamicfeatures.characterslist.ui.list

/**
 * Different interaction events for [CharactersListFragment].
 */
sealed class CharactersListViewEvent {

    /**
     * Open character detail view.
     *
     * @param id Character identifier
     */
    data class OpenCharacterDetail(
        val id: Long,
        val username: String,
        val email: String,
        val image: String
    ) : CharactersListViewEvent()
}
