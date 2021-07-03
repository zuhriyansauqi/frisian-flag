package com.zuhriyansauqi.frisianflag

import android.content.Context
import androidx.annotation.ArrayRes

fun loadAssets(context: Context, @ArrayRes id: Int): List<Int> {
    val tArray = context.resources.obtainTypedArray(id)
    val result = mutableListOf<Int>()

    for (i in 0 until tArray.length()) {
        result.add(tArray.getResourceId(i, 0))
    }

    tArray.recycle()
    return result.toList()
}