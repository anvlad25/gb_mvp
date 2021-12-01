package com.example.gb_mvp.main

import android.os.Bundle
import com.example.gb_mvp.R
import com.example.gb_mvp.abs.AbsActivity
import com.example.gb_mvp.databinding.ActivityMainBinding
import com.example.gb_mvp.users.UsersScreen
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.ktx.moxyPresenter

class MainActivity : AbsActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { MainPresenter(router, UsersScreen) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


}