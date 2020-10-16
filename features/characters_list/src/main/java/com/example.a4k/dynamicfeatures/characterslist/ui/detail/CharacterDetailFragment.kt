package com.example.a4k.dynamicfeatures.characterslist.ui.detail

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a4k.android.SampleApp.Companion.coreComponent
import com.example.a4k.commons.ui.base.BaseFragment
import com.example.a4k.commons.ui.extensions.observe
import com.example.a4k.commons.views.FileChooserDialog
import com.example.a4k.commons.views.ProgressBarDialog
import com.example.a4k.dynamicfeatures.characterslist.R
import com.example.a4k.dynamicfeatures.characterslist.databinding.FragmentCharacterDetailBinding
import com.example.a4k.dynamicfeatures.characterslist.ui.detail.di.CharacterDetailModule
import com.example.a4k.dynamicfeatures.characterslist.ui.detail.di.DaggerCharacterDetailComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_character_detail.view.*
import kotlinx.android.synthetic.main.list_item_character.view.*
import javax.inject.Inject

/**
 * View detail for selected character, displaying extra info and with option to add it to favorite.
 *
 * @see BaseFragment
 */
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        layoutId = R.layout.fragment_character_detail
    ) {

    @Inject
    lateinit var progressDialog: ProgressBarDialog

    @Inject
    lateinit var fileChooserDialog: FileChooserDialog

    private val args: CharacterDetailFragmentArgs by navArgs()

    lateinit var imageUri : Uri
    var appCompatImageView: ImageView? = null

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
        requireCompatActivity().observe(viewModel.state, ::onViewStateChange)
        viewModel.loadCharacterDetail(
            args.characterId,
            args.characterUsername,
            args.characterEmail,
            args.characterImage
        )
        appCompatImageView = view.character_image
        view.circleMenu.setOnItemClickListener { menuButton ->
            if (menuButton.favorite != null)
                fileChooserDialog.show()
            else if (menuButton.search != null) openCamera()
        }
    }

    fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
         imageUri =
             context?.getContentResolver()
                 ?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!;
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent,100)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharacterDetailComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .characterDetailModule(CharacterDetailModule(this))
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
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view state change on [CharacterDetailViewState].
     *
     * @param viewState State of character detail.
     */
    private fun onViewStateChange(viewState: CharacterDetailViewState) {
        when (viewState) {
            is CharacterDetailViewState.Loading ->
                progressDialog.show(R.string.character_detail_dialog_loading_text)
            is CharacterDetailViewState.Error ->
                progressDialog.dismissWithErrorMessage(R.string.character_detail_dialog_error_text)
            is CharacterDetailViewState.AddedToFavorite ->
                Snackbar.make(
                    requireView(),
                    R.string.character_detail_added_to_favorite_message,
                    Snackbar.LENGTH_LONG
                ).show()
            is CharacterDetailViewState.Dismiss ->
                findNavController().navigateUp()
            else -> progressDialog.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri = imageUri
                activity!!.contentResolver.notifyChange(selectedImage, null)
                val cr = activity!!.contentResolver
                val bitmap: Bitmap
                try {
                    bitmap = MediaStore.Images.Media
                        .getBitmap(cr, selectedImage)
                    appCompatImageView?.setImageBitmap(bitmap)
                    Toast.makeText(
                        activity, selectedImage.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(activity, "Failed to load", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
