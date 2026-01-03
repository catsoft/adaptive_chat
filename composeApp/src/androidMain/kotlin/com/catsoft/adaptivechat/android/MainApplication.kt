package com.catsoft.adaptivechat.android

import android.app.Application
import com.catsoft.adaptivechat.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.option.viewModelScopeFactory

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@MainApplication)

            options(viewModelScopeFactory())

            modules(appModule)
        }
    }
}
