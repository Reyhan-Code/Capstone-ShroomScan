package com.dicoding.capstone.view.scan

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstone.R
import com.dicoding.capstone.view.result.ResultActivity
import com.yalantis.ucrop.UCrop
import java.io.File

class ScanViewModel (application: Application) : AndroidViewModel(application){


    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> = _currentImageUri


    private val _croppedImage = MutableLiveData<Uri?>()
    val croppedImage: LiveData<Uri?> = _croppedImage

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun setCurrentImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun setCroppedImage(uri: Uri?) {
        _croppedImage.value = uri
    }

    fun showToast(message: String) {
        _toastMessage.value = message
    }

    fun startUCrop(sourceUri: Uri, context: Context) {
        val fileName = "cropped_image_${System.currentTimeMillis()}.jpg"
        val destinationUri = Uri.fromFile(File(context.cacheDir, fileName))

        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1000, 1000)
            .start(context as Activity)
    }

    fun analyzeImage(context: Context) {
        val intent = Intent(context, ResultActivity::class.java)
        _croppedImage.value?.let { uri ->
            intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, uri.toString())
        } ?: showToast(context.getString(R.string.image_classifier_failed))
    }

    fun moveToResult(context: Context) {
        val intent = Intent(context, ResultActivity::class.java)
        _croppedImage.value?.let { uri ->
            intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, uri.toString())
            context.startActivity(intent)
        } ?: showToast(context.getString(R.string.image_classifier_failed))
    }



}