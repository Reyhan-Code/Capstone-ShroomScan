package com.dicoding.capstone.view.scan

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yalantis.ucrop.UCrop
import java.io.File

class ScanViewModel (application: Application) : AndroidViewModel(application){


    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> get() = _currentImageUri

    private val _croppedImageUri = MutableLiveData<Uri?>()
    val croppedImageUri: LiveData<Uri?> get() = _croppedImageUri

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun setCroppedImageUri(uri: Uri?) {
        _croppedImageUri.value = uri
    }

    fun startUCrop(sourceUri: Uri, activity: AppCompatActivity) {
        val fileName = "cropped_image_${System.currentTimeMillis()}.jpg"
        val destinationUri = Uri.fromFile(File(getApplication<Application>().cacheDir, fileName))

        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1000, 1000)
            .start(activity)
    }

    fun handleUCropResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == UCrop.REQUEST_CROP && resultCode == AppCompatActivity.RESULT_OK) {
            val resultUri = UCrop.getOutput(data!!)
            resultUri?.let {
                setCroppedImageUri(it)
            } ?: showToast("Failed to crop image")
        } else if (requestCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            showToast("Crop error: ${cropError?.message}")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}