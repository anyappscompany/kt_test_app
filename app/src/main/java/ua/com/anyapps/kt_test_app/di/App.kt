package ua.com.anyapps.kt_test_app.di

import android.app.Application
import ua.com.anyapps.kt_test_app.di.component.ApplicationComponent
import ua.com.anyapps.kt_test_app.di.component.DaggerApplicationComponent
import ua.com.anyapps.kt_test_app.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initializeDagger()
    }

    private fun initializeDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getApplciationComponent(): ApplicationComponent {
        return applicationComponent
    }
}