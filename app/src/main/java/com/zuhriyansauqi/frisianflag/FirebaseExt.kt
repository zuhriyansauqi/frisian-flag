package com.zuhriyansauqi.frisianflag

import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

fun Firebase.logEventPageContent(name: String, type: String) {
    analytics.logEvent(EVENT_PAGE_CONTENT, bundleOf(
        PARAMS_NAME to name,
        PARAMS_TYPE to type
    ))
}

private const val EVENT_PAGE_CONTENT = "page_content"
private const val PARAMS_NAME = "name"
private const val PARAMS_TYPE = "type"
