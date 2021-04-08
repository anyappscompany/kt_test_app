package ua.com.anyapps.kt_test_app.utils

import ua.com.anyapps.kt_test_app.network.pojo.Doc

interface SearchResultCellClickListener {
    fun onCellClickListener(doc: Doc)
}