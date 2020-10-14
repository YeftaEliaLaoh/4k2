package com.example.a4k.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.a4k.commons.ui.base.BaseViewHolder
import com.example.a4k.dynamicfeatures.characterslist.ui.databinding.ListItemCharacterBinding
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.example.a4k.dynamicfeatures.characterslist.ui.list.model.CharacterItem

/**
 * Class describes character view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class CharacterViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemCharacterBinding>(
    binding = ListItemCharacterBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel Character list view model.
     * @param item Character list item.
     */
    fun bind(viewModel: CharactersListViewModel, item: CharacterItem) {
        binding.viewModel = viewModel
        binding.character = item
        binding.executePendingBindings()
    }
}
