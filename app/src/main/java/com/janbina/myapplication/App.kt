package com.janbina.myapplication

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksViewModelConfigFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Mavericks.viewModelConfigFactory = MavericksViewModelConfigFactory(applicationContext)
    }

}