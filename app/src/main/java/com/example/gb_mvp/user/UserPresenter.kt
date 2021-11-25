package com.example.gb_mvp.user

import com.example.gb_mvp.adapter.IRepoListPresenter
import com.example.gb_mvp.adapter.IUserListPresenter
import com.example.gb_mvp.adapter.RepoItemView
import com.example.gb_mvp.adapter.UserItemView
import com.example.gb_mvp.data.GithubUsersRepo
import com.example.gb_mvp.data.UserRepos
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<UserView>() {

    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<UserRepos>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.setRepo(repo.name)
        }
    }

    val reposListPresenter = ReposListPresenter()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        viewState.init()
        loadData()

        reposListPresenter.itemClickListener = { itemView ->
            viewState.showDialogRepo(reposListPresenter.repos[itemView.pos])
        }
    }


    private fun loadData() {
        disposables.add(
            userRepository
                .getUserByLogin(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(viewState::showUser, {})
        )

        disposables.add(
            userRepository
                .getUserRepo(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ repos ->
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {})
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        disposables.clear()
    }

}