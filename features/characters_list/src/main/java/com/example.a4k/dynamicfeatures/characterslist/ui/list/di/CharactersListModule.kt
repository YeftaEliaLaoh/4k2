package com.example.a4k.dynamicfeatures.characterslist.ui.list.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.example.a4k.commons.ui.extensions.viewModel
import com.example.a4k.core.di.scopes.FeatureScope
import com.example.a4k.core.network.repositiories.Repository
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.example.a4k.dynamicfeatures.characterslist.ui.list.adapter.CharactersListAdapter
import com.example.a4k.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.example.a4k.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSource
import com.example.a4k.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [CharactersListComponent].
 *
 * @see Module
 */
@Module
class CharactersListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: CharactersListFragment
) {

    /**
     * Create a provider method binding for [CharactersListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListViewModel(
        dataFactory: CharactersPageDataSourceFactory
    ) = fragment.viewModel {
        CharactersListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [CharactersPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesCharactersPageDataSource(
        viewModel: CharactersListViewModel,
        repository: Repository,
        mapper: CharacterItemMapper
    ) = CharactersPageDataSource(
            repository = repository,
            scope = viewModel.viewModelScope,
            mapper = mapper
        )

    /**
     * Create a provider method binding for [CharacterItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharacterItemMapper() = CharacterItemMapper()

    /**
     * Create a provider method binding for [CharactersListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesCharactersListAdapter(
        viewModel: CharactersListViewModel
    ) = CharactersListAdapter(viewModel)
}
