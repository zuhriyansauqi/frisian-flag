package com.zuhriyansauqi.frisianflag.page

import android.os.Bundle
import android.view.View
import com.google.firebase.ktx.Firebase
import com.zuhriyansauqi.frisianflag.SlideShowFragment
import com.zuhriyansauqi.frisianflag.R
import com.zuhriyansauqi.frisianflag.logEventPageContent

class StuntingFragment : SlideShowFragment(R.array.stun_assets) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Firebase.logEventPageContent(PAGE_NAME, PAGE_NAME)
    }

    companion object {
        private const val PAGE_NAME = "Stunting & Pencegahannya"
        private const val PAGE_TYPE = "slide_show"
    }
}