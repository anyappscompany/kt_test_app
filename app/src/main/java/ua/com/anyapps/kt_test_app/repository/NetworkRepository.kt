package ua.com.anyapps.kt_test_app.repository

import io.reactivex.Single
import ua.com.anyapps.kt_test_app.network.pojo.Books

interface NetworkRepository {
    fun getBooks(q: String): Single<Books>
}