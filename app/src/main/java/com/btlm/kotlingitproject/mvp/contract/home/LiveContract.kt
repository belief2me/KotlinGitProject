package com.btlm.kotlingitproject.mvp.contract.home

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.live.MulLive

/**
 * Created by Administrator on 2017/12/20.
 */
interface LiveContract {
    interface View : BaseContract.BaseView{
        fun showMulLive(mulLives : List<MulLive>)

    }
    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getLiveData()
    }
}