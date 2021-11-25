package com.example.gb_mvp.adapter

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
interface IRepoListPresenter : IListPresenter<RepoItemView>