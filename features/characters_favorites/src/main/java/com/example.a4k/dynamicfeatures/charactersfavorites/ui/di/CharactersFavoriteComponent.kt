package com.example.a4k.dynamicfeatures.charactersfavorites.ui.di

import com.example.a4k.core.di.CoreComponent
import com.example.a4k.core.di.scopes.FeatureScope
import com.example.a4k.dynamicfeatures.charactersfavorites.ui.CharactersFavoriteFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [CharactersFavoriteModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [CharactersFavoriteModule::class],
    dependencies = [CoreComponent::class])
interface CharactersFavoriteComponent {

    /**
     * Inject dependencies on component.
     *
     * @param favoriteFragment Favorite component.
     */
    fun inject(favoriteFragment: CharactersFavoriteFragment)
}
