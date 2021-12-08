package com.example.gb_mvp.users

import com.example.gb_mvp.adapter.IUserListPresenter
import com.example.gb_mvp.adapter.UserItemView
import com.example.gb_mvp.data.user.GitHubUser
import com.example.gb_mvp.data.user.GitHubUserRepository
import com.example.gb_mvp.user.UserScreen
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GitHubUserRepository, private val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GitHubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login, user.avatar)
        }
    }

    val usersListPresenter = UsersListPresenter()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()


        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(UserScreen(usersListPresenter.users[itemView.pos].login))
        }
    }

    private fun loadData() {
        disposables.add(
            usersRepo
                .getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { usersList ->
                        usersListPresenter.users.addAll(usersList)
                        viewState.updateList()
                    },
                    { error: Throwable ->
                        viewState.showError(
                            error
                        )
                    })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.dispose()
    }

}
