package com.zuhriyansauqi.frisianflag.page

import android.os.Bundle
import android.view.View
import com.google.firebase.ktx.Firebase
import com.zuhriyansauqi.frisianflag.SlideShowFragment
import com.zuhriyansauqi.frisianflag.R
import com.zuhriyansauqi.frisianflag.logEventPageContent

class HPKFragment :
    SlideShowFragment(R.array.hpk_assets, R.array.hpk_backgrounds, R.array.hpk_background_indexes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Firebase.logEventPageContent(PAGE_NAME, PAGE_TYPE)
    }

    companion object {
        private const val PAGE_NAME = "1000 Hari Pertama Kehidupan"
        private const val PAGE_TYPE = "slide_show"
    }
}