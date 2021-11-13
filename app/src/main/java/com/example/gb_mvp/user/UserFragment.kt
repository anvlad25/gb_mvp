package com.example.gb_mvp.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gb_mvp.App
import com.example.gb_mvp.arguments
import com.example.gb_mvp.databinding.FragmentUserBinding
import com.example.gb_mvp.main.BackButtonListener
import com.example.gb_mvp.data.GithubUser
import com.example.gb_mvp.data.GithubUsersRepo
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        private const val ARG_USER_LOGIN = "arg_user_login"
        fun newInstance(userLogin: String) = UserFragment().arguments(ARG_USER_LOGIN to userLogin)
    }

    private var viewBinding: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin,
            GithubUsersRepo(),
            App.instance.router
        )
    }

    override fun showUser(user: GithubUser) {
        viewBinding?.userLogin?.text = user.login
    }

    override fun backPressed() = presenter.backPressed()
}