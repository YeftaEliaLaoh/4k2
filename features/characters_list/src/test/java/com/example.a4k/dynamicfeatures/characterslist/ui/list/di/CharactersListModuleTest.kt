package com.example.a4k.dynamicfeatures.characterslist.ui.list.di

import androidx.lifecycle.ViewModel
import com.example.a4k.commons.ui.extensions.viewModel
import com.example.a4k.core.network.repositiories.Repository
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListFragment
import com.example.a4k.dynamicfeatures.characterslist.ui.list.CharactersListViewModel
import com.example.a4k.dynamicfeatures.characterslist.ui.list.model.CharacterItemMapper
import com.example.a4k.dynamicfeatures.characterslist.ui.list.paging.CharactersPageDataSourceFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharactersListModuleTest {

    @MockK
    lateinit var fragment: CharactersListFragment
    lateinit var module: CharactersListModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersListModule_ShouldSetUpCorrectly() {
        module = CharactersListModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersListViewModel() {
        mockkStatic("com.example.a4k.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersListViewModel>()

        val factoryCaptor = slot<() -> CharactersListViewModel>()
        val dataFactory = mockk<CharactersPageDataSourceFactory>(relaxed = true)
        module = CharactersListModule(fragment)
        module.providesCharactersListViewModel(dataFactory)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(dataFactory, factoryCaptor.captured().dataSourceFactory)
    }

    @Test
    fun verifyProvidedCharactersPageDataSource() {
        val repository = mockk<Repository>(relaxed = true)
        val mapper = mockk<CharacterItemMapper>(relaxed = true)
        val viewModel = mockk<CharactersListViewModel>(relaxed = true)
        val scope = mockk<CoroutineScope>()
        every { viewModel.viewModelScope } returns scope

        module = CharactersListModule(fragment)
        val dataSource = module.providesCharactersPageDataSource(
            viewModel = viewModel,
            repository = repository,
            mapper = mapper
        )

        assertEquals(repository, dataSource.repository)
        assertEquals(mapper, dataSource.mapper)
        assertEquals(scope, dataSource.scope)
    }
}
