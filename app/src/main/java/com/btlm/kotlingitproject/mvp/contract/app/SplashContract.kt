package com.btlm.kotlingitproject.mvp.contract.app

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.app.Splash

/**
 * Created by Administrator on 2017/12/16.
 */
interface SplashContract {
    interface View : BaseContract.BaseView{
        fun showSplash(splash : Splash)

        fun showCountDown(count : Int)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T>{

        fun getSplashData()

        fun setCountDown()
    }

}