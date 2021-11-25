package com.example.gb_mvp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_mvp.databinding.ItemRepoBinding

class ReposRVAdapter(private val presenter: IRepoListPresenter) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val viewBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        RepoItemView {
        override var pos = -1

        override fun setRepo(repo: String) = with(viewBinding) {
            tvRepo.text = repo
        }
    }
}