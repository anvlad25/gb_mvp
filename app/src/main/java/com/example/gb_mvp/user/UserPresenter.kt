package com.example.gb_mvp.user

import com.example.gb_mvp.adapter.IRepoListPresenter
import com.example.gb_mvp.adapter.RepoItemView
import com.example.gb_mvp.data.repository.GitHubRepository
import com.example.gb_mvp.data.user.GitHubUserRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val userRepository: GitHubUserRepository,
    private val router: Router
) : MvpPresenter<UserView>() {

    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GitHubRepository>()
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
                .getUserRepositories(userLogin)
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