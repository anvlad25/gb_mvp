package com.example.gb_mvp.users

import com.example.gb_mvp.adapter.IUserListPresenter
import com.example.gb_mvp.adapter.UserItemView
import com.example.gb_mvp.data.GithubUser
import com.example.gb_mvp.data.GithubUsersRepo
import com.example.gb_mvp.user.UserScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()


        usersListPresenter.itemClickListener = { itemView ->
            usersRepo
                .getLoginByPos(itemView.pos)
                .subscribe { bla -> router.navigateTo(UserScreen(bla)) }
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        users.subscribe { usersList -> usersListPresenter.users.addAll(usersList) }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
