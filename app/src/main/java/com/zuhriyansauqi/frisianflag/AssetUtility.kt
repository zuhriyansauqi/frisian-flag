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