package com.example.gb_mvp.user

import com.example.gb_mvp.data.repository.GitHubRepository
import com.example.gb_mvp.data.user.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView : MvpView {
    fun showUser(user: GitHubUser)
    fun showDialogRepo(repo: GitHubRepository)
    fun init()
    fun updateList()
}