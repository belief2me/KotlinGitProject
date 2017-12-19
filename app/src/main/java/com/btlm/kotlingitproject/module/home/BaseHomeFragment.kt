package com.btlm.kotlingitproject.module.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.base.BaseFragment

/**
 * Created by Administrator on 2017/12/19.
 */
abstract class BaseHomeFragment : BaseFragment() {

    var mToolbar : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //支持menu
        setHasOptionsMenu(true)
    }

    override fun initWidget() {
        initToolbar()
    }

    private fun initToolbar(){
        mToolbar = mRootView?.findViewById<Toolbar>(R.id.toolbar)
        mToolbar?.let {
            it.title = ""
            (activity as AppCompatActivity).setSupportActionBar(mToolbar)
            it.inflateMenu(R.menu.menu_main)
        }
    }
}