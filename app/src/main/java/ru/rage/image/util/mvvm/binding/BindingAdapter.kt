package ru.rage.image.util.mvvm.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.io.File

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:file")
    fun loadImage(imageView: ImageView, file: File?) {
        if (file != null)
            Picasso.get().load(file).into(imageView)
    }

}