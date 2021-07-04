package com.zuhriyansauqi.frisianflag

import android.content.Context
import android.net.Uri
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun loadAssets(context: Context, @ArrayRes id: Int): List<Int> {
    val tArray = context.resources.obtainTypedArray(id)
    val result = mutableListOf<Int>()

    for (i in 0 until tArray.length()) {
        result.add(tArray.getResourceId(i, 0))
    }

    tArray.recycle()
    return result.toList()
}

fun loadBackgrounds(
    context: Context,
    @ArrayRes indexes: Int,
    @ArrayRes backgrounds: Int
): Map<Int, Int> {
    val tArrayIndexes = context.resources.obtainTypedArray(indexes)
    val tArray = context.resources.obtainTypedArray(backgrounds)
    val result = mutableMapOf<Int, Int>()

    for (i in 0 until tArray.length()) {
        result[tArrayIndexes.getInt(i, 0)] = tArray.getResourceId(i, 0)
    }

    tArrayIndexes.recycle()
    tArray.recycle()

    return result.toMap()
}

fun loadVideoUri(context: Context, @StringRes fileName: Int): Uri {
    val name = context.getString(fileName)
    return Uri.parse("assets:///$name")
}
