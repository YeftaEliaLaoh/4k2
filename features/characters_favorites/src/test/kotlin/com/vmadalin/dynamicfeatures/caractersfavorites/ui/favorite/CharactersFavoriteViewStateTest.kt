package com.example.a4k.dynamicfeatures.caractersfavorites.ui

import com.example.a4k.dynamicfeatures.charactersfavorites.ui.CharactersFavoriteViewState
import org.junit.Assert
import org.junit.Test

class CharactersFavoriteViewStateTest {

    lateinit var state: CharactersFavoriteViewState

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = CharactersFavoriteViewState.Empty

        Assert.assertTrue(state.isEmpty())
        Assert.assertFalse(state.isListed())
    }

    @Test
    fun setStateAsListed_ShouldBeSettled() {
        state = CharactersFavoriteViewState.Listed

        Assert.assertTrue(state.isListed())
        Assert.assertFalse(state.isEmpty())
    }
}
