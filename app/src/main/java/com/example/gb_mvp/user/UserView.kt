package com.example.gb_mvp.user

import com.example.gb_mvp.data.GithubUser
import com.example.gb_mvp.data.UserRepos
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView : MvpView {
    fun showUser(user: GithubUser)
    fun showDialogRepo(repo: UserRepos)
    fun init()
    fun updateList()
}