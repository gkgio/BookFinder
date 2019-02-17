package com.gig.bookfinder.presentetion.di

import com.gig.bookfinder.data.errorhandler.ApiErrors
import com.gig.bookfinder.data.network.IService
import com.gig.bookfinder.data.network.REST
import com.gig.bookfinder.presentetion.search.BookActivity
import com.gig.bookfinder.presentetion.search.BookActivityContract
import dagger.Component

@ActivityScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectBookActivity(target: BookActivity)
    fun presenter(): BookActivityContract.Presenter
}