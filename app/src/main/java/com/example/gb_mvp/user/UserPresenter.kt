package com.example.gb_mvp.user

import com.example.gb_mvp.data.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        userRepository
            .getUserByLogin(userLogin)
            .subscribe(viewState::showUser)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}