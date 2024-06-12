package com.dicoding.capstone.view.scan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityScanBinding
import com.dicoding.capstone.utils.getImageUri
import com.yalantis.ucrop.UCrop

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding

    private var currentImageUri: Uri? = null
    private val viewModel: ScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraButton.setOnClickListener { startCamera() }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.analyzeButton.setOnClickListener {
            viewModel.currentImageUri.value?.let {
                viewModel.analyzeImage(this)
                viewModel.moveToResult(this)
            } ?: run {
                viewModel.showToast(getString(R.string.empty_image_warning))
            }
        }

        viewModel.croppedImage.observe(this, Observer { uri ->
            uri?.let { binding.previewImageView.setImageURI(it) }
        })

        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let { showToast(it) }
        })
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.setCurrentImageUri(uri)
            showImage()
            viewModel.currentImageUri.value?.let { viewModel.startUCrop(it, this) }
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startCamera() {
        viewModel.setCurrentImageUri(getImageUri(this))
        viewModel.currentImageUri.value?.let { launcherIntentCamera.launch(it) }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
            viewModel.currentImageUri.value?.let { viewModel.startUCrop(it, this) }
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            val resultUri = UCrop.getOutput(data!!)
            resultUri?.let {
                viewModel.setCroppedImage(it)
            } ?: showToast("Failed to crop image")
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            showToast("Crop error: ${cropError?.message}")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}