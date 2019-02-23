package com.gig.bookfinder

import android.app.Application
import com.gig.bookfinder.data.network.REST
import com.gig.bookfinder.di.AppComponent
import com.gig.bookfinder.di.AppModule
import com.gig.bookfinder.di.DaggerAppComponent

class BookFinderApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()

        initREST()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun initREST() {
        REST.instance = appComponent.rest()
    }
}