package ua.com.anyapps.kt_test_app.di.component

import dagger.Component
import ua.com.anyapps.kt_test_app.ui.activities.MainActivity
import ua.com.anyapps.kt_test_app.di.module.AppModule
import ua.com.anyapps.kt_test_app.di.module.NetworkModule
import ua.com.anyapps.kt_test_app.di.module.SharedPreferencesModule
import ua.com.anyapps.kt_test_app.repository.NetworkRepositoryImpl
import ua.com.anyapps.kt_test_app.viewmodel.SharedMainViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class, SharedPreferencesModule::class, NetworkModule::class
    )
)
    interface ApplicationComponent {
    fun inject(into: MainActivity)
    fun inject(into: NetworkRepositoryImpl)
}