package com.example.gb_mvp.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_mvp.App
import com.example.gb_mvp.adapter.UsersRVAdapter
import com.example.gb_mvp.data.GithubUsersRepo
import com.example.gb_mvp.databinding.FragmentUsersBinding
import com.example.gb_mvp.main.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import androidx.recyclerview.widget.DividerItemDecoration

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(),
            App.instance.router
        )
    }
    private var adapter: UsersRVAdapter? = null

    private var viewBinding: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun init() {
        viewBinding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        viewBinding?.rvUsers?.adapter = adapter
        viewBinding?.rvUsers?.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()
}