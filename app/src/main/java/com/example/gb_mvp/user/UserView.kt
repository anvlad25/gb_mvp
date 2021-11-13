package com.example.gb_mvp.user

import com.example.gb_mvp.data.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface UserView : MvpView {
    @SingleState
    fun showUser(user: GithubUser)
}