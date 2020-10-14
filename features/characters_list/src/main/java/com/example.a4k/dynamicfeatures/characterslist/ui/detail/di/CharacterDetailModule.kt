package com.example.a4k.dynamicfeatures.characterslist.ui.detail.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.example.a4k.commons.ui.extensions.viewModel
import com.example.a4k.core.database.characterfavorite.CharacterFavoriteRepository
import com.example.a4k.core.di.scopes.FeatureScope
import com.example.a4k.core.network.repositiories.MarvelRepository
import com.example.a4k.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment
import com.example.a4k.dynamicfeatures.characterslist.ui.detail.CharacterDetailViewModel
import com.example.a4k.dynamicfeatures.characterslist.ui.detail.model.CharacterDetailMapper
import com.example.a4k.views.ProgressBarDialog
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharacterDetailComponent].
 *
 * @see Module
 */
@Module
class CharacterDetailModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CharacterDetailFragment
) {

    /**
     * Create a provider method binding for [CharacterDetailViewModel].
     *
     * @param marvelRepository
     * @param characterFavoriteRepository handler character favorite repository
     * @param characterDetailMapper mapper to parse view model
     *
     * @return instance of view model.
     */
    @FeatureScope
    @Provides
    fun providesCharacterDetailViewModel(
        marvelRepository: MarvelRepository,
        characterFavoriteRepository: CharacterFavoriteRepository,
        characterDetailMapper: CharacterDetailMapper
    ) = fragment.viewModel {
        CharacterDetailViewModel(
            marvelRepository = marvelRepository,
            characterFavoriteRepository = characterFavoriteRepository,
            characterDetailMapper = characterDetailMapper
        )
    }

    /**
     * Create a provider method binding for [CharacterDetailMapper].
     *
     * @return instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterDetailMapper() = CharacterDetailMapper()

    /**
     * Create a provider method binding for [ProgressBarDialog].
     *
     * @return instance of dialog.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesProgressBarDialog() = ProgressBarDialog(fragment.requireContext())
}