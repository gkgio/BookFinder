package com.gig.bookfinder.di

import com.gig.bookfinder.data.network.REST
import com.gig.bookfinder.presentetion.search.BookActivity
import com.gig.bookfinder.presentetion.search.BookActivityContract
import dagger.Component

@ActivityScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun rest(): REST

    fun injectBookActivity(target: BookActivity)
    fun presenter(): BookActivityContract.Presenter
}