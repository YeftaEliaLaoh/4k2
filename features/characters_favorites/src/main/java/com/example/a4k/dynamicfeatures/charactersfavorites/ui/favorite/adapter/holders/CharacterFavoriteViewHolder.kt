package com.example.a4k.dynamicfeatures.charactersfavorites.ui.favorite.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.a4k.commons.ui.base.BaseViewHolder
import com.example.a4k.core.database.characterfavorite.CharacterFavorite
import com.example.a4k.dynamicfeatures.charactersfavorites.databinding.ListItemCharactersFavoriteBinding

/**
 * Class describes character favorite view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class CharacterFavoriteViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharactersFavoriteBinding>(
    binding = ListItemCharactersFavoriteBinding.inflate(inflater)
) {
    /**
     * Bind view data binding variables.
     *
     * @param characterFavorite Character favorite to bind.
     */
    fun bind(characterFavorite: CharacterFavorite) {
        binding.character = characterFavorite
        binding.executePendingBindings()
    }
}
