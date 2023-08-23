package edu.uptsgm.sedd.framework.android.app

import android.app.Application
import edu.uptsgm.sedd.framework.di.koin.module.appModule
import edu.uptsgm.sedd.framework.di.koin.module.loginModule
import edu.uptsgm.sedd.framework.di.koin.module.studentHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SeddApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SeddApplication)
            modules(appModule)
        }
    }

}