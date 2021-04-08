package ua.com.anyapps.kt_test_app.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import ua.com.anyapps.kt_test_app.di.App
import ua.com.anyapps.kt_test_app.network.RestApi
import ua.com.anyapps.kt_test_app.network.pojo.Books
import ua.com.anyapps.kt_test_app.network.pojo.Doc
import javax.inject.Inject

class NetworkRepositoryImpl: NetworkRepository {
    @Inject
    lateinit var restApi: RestApi

    init {
        App.applicationComponent.inject(this)
    }

    override fun getBooks(q: String): Single<Books> {
        return restApi.getBooks(q)
    }
}