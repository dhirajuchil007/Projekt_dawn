package com.velocityappsdj.projektdawn.adapters

import android.util.Log
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:error")

fun setProjectNameError(view: TextInputEditText, errorMessage: String) {
    Log.d(
        "BindingAdapters",
        "getProjectNameError() called with: view = $view, errorMessage = $errorMessage"
    )
    view.error = errorMessage
}