package com.gig.bookfinder

import android.app.Application
import com.gig.bookfinder.data.network.REST
import com.gig.bookfinder.presentetion.di.AppComponent
import com.gig.bookfinder.presentetion.di.AppModule
import com.gig.bookfinder.presentetion.di.DaggerAppComponent

class BookFinderApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}