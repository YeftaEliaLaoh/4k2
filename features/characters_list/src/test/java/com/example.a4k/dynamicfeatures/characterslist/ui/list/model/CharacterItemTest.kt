package com.example.a4k.dynamicfeatures.characterslist.ui.list.model

import org.junit.Assert
import org.junit.Test

class CharacterItemTest {

    @Test
    fun createCharacterItem_ShouldAddCorrectAttributes() {
        val id = 131231L
        val name = "A.I.M"
        val description = "AIM is a terrorist organization bent on destroying the world."

        val character = CharacterItem(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )

        Assert.assertEquals(id, character.id)
        Assert.assertEquals(name, character.name)
        Assert.assertEquals(description, character.description)
        Assert.assertEquals(imageUrl, character.imageUrl)
    }
}
