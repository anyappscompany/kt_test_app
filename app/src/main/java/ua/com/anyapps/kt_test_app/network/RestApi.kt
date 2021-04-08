package ua.com.anyapps.kt_test_app.network

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.anyapps.kt_test_app.network.pojo.Books

interface RestApi {
        @GET("search.json")
        fun getBooks(@Query("q") q: String): Single<Books>
}