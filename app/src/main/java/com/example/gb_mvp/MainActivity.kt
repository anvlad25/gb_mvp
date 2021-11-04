package com.example.gb_mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gb_mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initButtonClick()
    }

    private fun initButtonClick() {
        binding?.let {
            it.btnCounter1.setOnClickListener {
                presenter.buttonIncrement1()
            }

            it.btnCounter2.setOnClickListener {
                presenter.buttonIncrement2()
            }

            it.btnCounter3.setOnClickListener {
                presenter.buttonIncrement3()
            }
        }
    }

    override fun setButtonValue1(text: String) {
        binding?.btnCounter1?.text = text
    }

    override fun setButtonValue2(text: String) {
        binding?.btnCounter2?.text = text
    }

    override fun setButtonValue3(text: String) {
        binding?.btnCounter3?.text = text
    }
}