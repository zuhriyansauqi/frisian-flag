package com.zuhriyansauqi.frisianflag.page

import android.os.Bundle
import android.view.View
import com.google.firebase.ktx.Firebase
import com.zuhriyansauqi.frisianflag.R
import com.zuhriyansauqi.frisianflag.SlideShowFragment
import com.zuhriyansauqi.frisianflag.logEventPageContent

class FrisianFlagFragment
    : SlideShowFragment(R.array.ff_assets, R.array.ff_backgrounds, R.array.ff_background_indexes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Firebase.logEventPageContent(PAGE_NAME, PAGE_TYPE)
    }

    companion object {
        private const val PAGE_NAME = "Frisian Flag Indonesia"
        private const val PAGE_TYPE = "slide_show"
    }
}
