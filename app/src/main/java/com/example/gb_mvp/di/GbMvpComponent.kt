package com.example.gb_mvp.di

import android.content.Context
import com.example.gb_mvp.App
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, UserModule::class])
interface GbMvpComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): GbMvpComponent

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder
    }
}