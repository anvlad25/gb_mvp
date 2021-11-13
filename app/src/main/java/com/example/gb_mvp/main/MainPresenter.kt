package com.example.gb_mvp.main

import com.example.gb_mvp.users.UsersScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: UsersScreen) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens)
    }

    fun backClicked() {
        router.exit()
    }
}