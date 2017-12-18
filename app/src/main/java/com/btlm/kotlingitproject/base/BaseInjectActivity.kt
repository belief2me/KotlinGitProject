package com.btlm.kotlingitproject.base

import android.view.View
import com.btlm.kotlingitproject.KotlinGitApplication
import com.btlm.kotlingitproject.di.component.ActivityComponent
import com.btlm.kotlingitproject.di.component.DaggerActivityComponent
import com.btlm.kotlingitproject.ext.getComponent
import com.yoyiyi.soleil.di.module.ActivityModule
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/16.
 */
abstract class BaseInjectActivity<T : BaseContract.BasePresenter<*>> : BaseActivity(),BaseContract.BaseView{
    @Inject
    lateinit var mPresenter : T

    // 优先使用属性
    protected val activityModule : ActivityModule get() = ActivityModule(this)

    protected val activityComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
            .appComponent(getComponent())
            .activityModule(activityModule)
            .build()

    override fun showError(msg: String) {
        mError?.visibility = View.VISIBLE
    }

    override fun complete() {
        mError?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
        KotlinGitApplication.instance.removeActivity(this)
    }
}