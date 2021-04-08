package ua.com.anyapps.kt_test_app.di.module

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.com.anyapps.kt_test_app.network.RestApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module(includes = arrayOf(AppModule::class))
@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun getApiInterface(retroFit: Retrofit): RestApi {
        return retroFit.create(RestApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://openlibrary.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun getOkHttpCleint(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }
}