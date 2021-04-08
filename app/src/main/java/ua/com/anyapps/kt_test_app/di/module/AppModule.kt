package ua.com.anyapps.kt_test_app.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(application: Application) {

    private lateinit var application: Application

    init {
        this.application = application
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    /*@Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }*/
}