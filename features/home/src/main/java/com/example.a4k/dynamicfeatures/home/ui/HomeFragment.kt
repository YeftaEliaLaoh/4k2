package com.example.a4k.dynamicfeatures.home.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.a4k.android.SampleApp
import com.example.a4k.commons.ui.base.BaseFragment
import com.example.a4k.commons.ui.extensions.setupWithNavController
import com.example.a4k.dynamicfeatures.home.R
import com.example.a4k.dynamicfeatures.home.databinding.FragmentHomeBinding
import com.example.a4k.dynamicfeatures.home.ui.di.DaggerHomeComponent
import com.example.a4k.dynamicfeatures.home.ui.di.HomeModule
import com.example.a4k.dynamicfeatures.home.ui.menu.ToggleThemeCheckBox
import com.example.dynamicfeature.setting.ui.language.LanguageDialog
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Home principal view containing bottom navigation bar with different characters tabs.
 *
 * @see BaseFragment
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {

    private val navGraphIds = listOf(
        R.navigation.navigation_characters_list_graph,
        R.navigation.navigation_characters_favorites_graph
    )

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
        setupToolbar()
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    /**
     * Called when all saved state has been restored into the view hierarchy of the fragment.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     * @see BaseFragment.onViewStateRestored
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    /**
     * Initialize the contents of the Fragment host's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @param inflater Inflater to instantiate menu XML files into Menu objects.
     * @see BaseFragment.onCreateOptionsMenu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        menu.findItem(R.id.menu_toggle_theme).apply {
            val actionView = this.actionView
            if (actionView is ToggleThemeCheckBox) {
                actionView.setOnCheckedChangeListener { _, _ ->
                    val popup = PopupMenu(context, actionView)
                    popup.menuInflater.inflate(R.menu.setting_menu, popup.menu)
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.setting_language -> {
                                LanguageDialog(context)
                            }
                            R.id.exit_app -> {
                                activity?.moveTaskToBack(true)
                                activity?.finish()
                            }
                        }
                        true
                    }
                    popup.show()
                }
            }
        }
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .coreComponent(SampleApp.coreComponent(requireContext()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // ============================================================================================
    //  Private setups methods
    // ============================================================================================

    /**
     * Configure app custom support action bar.
     */
    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.appBarLayout.toolbar)
    }

    /**
     * Configure app bottom bar via navigation graph.
     */
    private fun setupBottomNavigationBar() {
        val navController = viewBinding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_container,
            intent = requireActivity().intent
        )

        navController.observe(viewLifecycleOwner, Observer {
            viewModel.navigationControllerChanged(it)
            setupActionBarWithNavController(requireCompatActivity(), it)
        })
    }
}
