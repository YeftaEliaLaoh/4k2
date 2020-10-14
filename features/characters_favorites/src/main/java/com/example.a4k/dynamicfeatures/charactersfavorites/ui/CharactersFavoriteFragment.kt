package com.example.a4k.dynamicfeatures.charactersfavorites.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.a4k.SampleApp
import com.example.a4k.commons.ui.base.BaseFragment
import com.example.a4k.commons.ui.extensions.observe
import com.example.a4k.core.database.characterfavorite.CharacterFavorite
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.adapter.CharactersFavoriteAdapter
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.adapter.CharactersFavoriteTouchHelper
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.databinding.FragmentCharactersFavoriteListBinding
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.di.CharactersFavoriteModule
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.di.DaggerCharactersFavoriteComponent
import javax.inject.Inject

/**
 * View listing the added favorite characters with option to remove element by swiping.
 *
 * @see BaseFragment
 */
class CharactersFavoriteFragment :
    BaseFragment<FragmentCharactersFavoriteListBinding, CharactersFavoriteViewModel>(
        layoutId = R.layout.fragment_characters_favorite_list
    ) {

    @Inject
    lateinit var viewAdapter: CharactersFavoriteAdapter

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.data, ::onViewDataChange)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharactersFavoriteComponent
            .builder()
            .coreComponent(SampleApp.coreComponent(requireContext()))
            .charactersFavoriteModule(CharactersFavoriteModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersFavoriteList.apply {
            adapter = viewAdapter

            ItemTouchHelper(CharactersFavoriteTouchHelper {
                viewModel.removeFavoriteCharacter(viewAdapter.currentList[it])
            }).attachToRecyclerView(this)
        }
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view data change on [CharactersFavoriteViewModel].
     *
     * @param viewData List of favorite characters.
     */
    private fun onViewDataChange(viewData: List<CharacterFavorite>) {
        viewAdapter.submitList(viewData)
    }
}
