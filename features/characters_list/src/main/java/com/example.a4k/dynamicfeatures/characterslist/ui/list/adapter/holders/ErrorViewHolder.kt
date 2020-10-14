package com.example.a4k.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.a4k.commons.ui.base.BaseViewHolder
import com.example.a4k.dynamicfeatures.characterslist.databinding.ListItemErrorBinding
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListViewModel

/**
 * Class describes characters error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel character list view model.
     */
    fun bind(viewModel: CharactersListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
