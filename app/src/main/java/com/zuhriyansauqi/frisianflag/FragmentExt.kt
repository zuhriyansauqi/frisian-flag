package com.zuhriyansauqi.frisianflag

import android.media.MediaPlayer
import androidx.fragment.app.Fragment

fun Fragment.playClickSound(completion: (() -> Unit)? = null) {
    val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.click)
    mediaPlayer.setOnCompletionListener { completion?.invoke() }
    mediaPlayer.start()
}