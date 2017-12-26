package com.btlm.kotlingitproject.base

import android.view.View
import com.btlm.kotlingitproject.KotlinGitApplication
import com.btlm.kotlingitproject.di.component.DaggerFragmentComponent
import com.btlm.kotlingitproject.di.component.FragmentComponent
import com.btlm.kotlingitproject.di.module.FragmentModule
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/19.
 */

abstract class BaseInjectFragment<T : BaseContract.BasePresenter<*>> : BaseFragment(),BaseContract.BaseView {
    @Inject
    lateinit var mPresenter : T

    val fragmentModule : FragmentModule get() = FragmentModule(this)

    val fragmentComponent : FragmentComponent
        get() = DaggerFragmentComponent.builder()
            .appComponent(KotlinGitApplication.instance.appComponent)
            .fragmentModule(fragmentModule)
            .build()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showError(msg: String) {
        mError?.visibility = View.VISIBLE
    }

    override fun complete() {
        mError?.visibility = View.GONE
    }

}