package com.example.basiccalculator.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.basiccalculator.R
import com.example.basiccalculator.presenter.CalculatorPresenter
import com.example.basiccalculator.presenter.ICalculatorPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ICalculatorView {
    private lateinit var mPresenter: ICalculatorPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
        mPresenter = CalculatorPresenter(this)
    }

    override fun setValue(res: String) {
        txtScreen.text = res
    }

    @OnClick(
        R.id.zero,
        R.id.one,
        R.id.two,
        R.id.three,
        R.id.four,
        R.id.five,
        R.id.six,
        R.id.seven,
        R.id.eight,
        R.id.nine
    )
    fun onNumberClicked(button: Button) {
        mPresenter.numberClick(button.text as String)
    }

    @OnClick(R.id.add, R.id.subtract, R.id.divide, R.id.multiply)
    fun onOperationClicked(button: Button) {
        mPresenter.operationClick(button.text as String)
    }

    @OnClick(R.id.clear)
    fun onClearClicked() {
        mPresenter.clearClick()
    }

    @OnClick(R.id.equal)
    fun onEqualClicked() {
        mPresenter.equalClick()
    }
}