package com.example.a4k.dynamicfeatures.characterslist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.a4k.commons.ui.base.BaseViewHolder
import com.example.a4k.dynamicfeatures.characterslist.ui.databinding.ListItemLoadingBinding

/**
 * Class describes characters loading view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
