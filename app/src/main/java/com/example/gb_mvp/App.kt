package com.example.gb_mvp


import com.example.gb_mvp.di.DaggerGbMvpComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerGbMvpComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create(Router())
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()
}