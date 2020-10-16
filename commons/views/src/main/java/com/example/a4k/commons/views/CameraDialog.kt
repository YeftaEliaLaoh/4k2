package com.example.a4k.commons.views;

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.provider.MediaStore


class CameraDialog(
    context: Context
) : Dialog(context) {

    fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        val imageUri =
            context.getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

    }
}
